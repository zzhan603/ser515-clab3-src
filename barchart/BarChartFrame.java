import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;

@SuppressWarnings("serial")
class BarChartFrame extends Frame
{
	Hashtable<String, Color> colorMap = new Hashtable<String, Color>();

	protected Vector<Integer>	data;
	protected Vector<String>	labels;
	protected Vector<Color>		colors;

	Choice 	colorSelect;
	TextField	labelSelect;
	TextField	dataSelect;

	BarChart chart;

	class BarChartFrameControl extends WindowAdapter implements ActionListener 
	{
		// COMPLETED FIX FOR SER515 #2: adds an element to the colors(vector)
		


		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof Button)
			{
				String selectedColor = colorSelect.getSelectedItem();
        		Color color = colorMap.get(selectedColor);

        // COMPLETED FIX FOR SER515 #2: safety check to deal with missing color problem.
        	if (color == null) {
            color = Color.orange;   // color category1
            System.err.println("Warning: Color '" + selectedColor + "' not found in colorMap. Using orange as fallback.");
        }

        String labelText = labelSelect.getText().trim();
        String dataText = dataSelect.getText().trim();

        // here is for validation
        if (labelText.isEmpty() || dataText.isEmpty()) {
            System.err.println("Error: Label or data value cannot be empty.");
            return;
        }

        labels.addElement(labelText);
        data.addElement(new Integer(dataText));
        colors.addElement(color);

        chart.setData(data);
        chart.setColors(colors);
        chart.setLabels(labels);
        chart.repaint();
    }
    else if (e.getSource() instanceof Menu)
    {
        BarChartFrame.this.dispose();
        System.exit(0);
    }
		}
	
	}

	
	
	
	public void initData(String fname)  //COMPLETED FIX FOR SER515 #3:
{
    data = new Vector<Integer>();
    labels = new Vector<String>();
    colors = new Vector<Color>();

    colorMap.put("red", Color.red);
    colorMap.put("green", Color.green);
    colorMap.put("blue", Color.blue);
    colorMap.put("magenta", Color.magenta);
    colorMap.put("gray", Color.gray);
    colorMap.put("orange", Color.orange);                     // add missing color as options

    try (FileReader bridge = new FileReader(fname)) {
        StreamTokenizer tokens = new StreamTokenizer(bridge);

        while (tokens.nextToken() != StreamTokenizer.TT_EOF) {
            // Expected for the number
            if (tokens.ttype != StreamTokenizer.TT_NUMBER) {
                System.err.println("Warning: Expected number, skipping invalid line.");
                continue;
            }
            int number = (int) tokens.nval;

            // Expected for the label (string type)
            tokens.nextToken();
            if (tokens.ttype != StreamTokenizer.TT_WORD && tokens.sval == null) {
                System.err.println("Warning: Expected label, skipping line.");
                continue;
            }
            String label = tokens.sval;

            // Expected for the color name
            tokens.nextToken();
            String colorName = tokens.sval != null ? tokens.sval.toLowerCase() : "";
            Color color = colorMap.get(colorName);

            if (color == null) {
                System.err.println("Warning: Unknown color '" + colorName + "' for '" + label + "'. Using gray as fallback.");
                color = Color.gray;
            }

            data.addElement(number);
            labels.addElement(label);
            colors.addElement(color);
        }
    }
    catch (IOException e) {
        System.err.println("Error reading file: " + fname);
        e.printStackTrace();
    }
    catch (Exception e) {
        System.err.println("Unexpected error while parsing data file.");
        e.printStackTrace();
    }
	
} 

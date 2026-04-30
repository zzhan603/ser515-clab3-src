package solid3;

import java.util.HashMap;
import java.util.Map;



public interface IPower {
    int toPower(int n, int pow);
}


class PowerSimple implements IPower {

    @Override
    public int toPower(int n, int pow) {
        if (pow == 0) {
            return 1;
        }

        int res = 1;
        for (int i = 0; i < pow; i++) {
            res *= n;
        }
        return res;
    }
}

class PowerCached implements IPower {

    
    private static final Map<Integer, Map<Integer, Integer>> CACHE = new HashMap<>();

    @Override
    public int toPower(int n, int pow) {
        if (pow == 0) {
            return 1;
        }

        Map<Integer, Integer> entry = CACHE.computeIfAbsent(n, k -> new HashMap<>());

        return toCachedPower(entry, n, pow);
    }

    
    private int toCachedPower(Map<Integer, Integer> entry, int n, int pow) {
        Integer res = entry.get(pow);
        if (res != null) {
            return res;
        }

        if (pow == 0) {
            res = 1;
        } else {
            res = n * toCachedPower(entry, n, pow - 1);
        }

        entry.put(pow, res);
        return res;
    }
}
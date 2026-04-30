## 1. Boundary Cases for blackbox test
- Base = 0 with exponent = 0, 1, 5
- Exponent = 0 with various bases (such as: 1. positive, 2. negative, 3.zero)
- Negative bases with exponent 0, 1, 2, 3

## 2. Failure Cases (Negative Testing)
- All cases where exponent is negative → should throw IllegalArgumentException
- Tested with positive, negative, and zero base at this time only

## 3. Equivalence Classes
- Base = 1 (any exponent returns back to the value, which is equal to 1)
- Base = 0 (returns 0 for exponent > 0, 1 for exponent = 0)

# 12-BitManipulation & Mathematics

Welcome to the **Bit Manipulation & Mathematics** module of the Java DSA Preparation repository.

## Overview
Bit manipulation operates directly at the bit level of data types. It allows high-speed arithmetic, compact flag encoding, and elegant constant-space algorithm formulations.

---

## Planned Directory Structure
```
12-BitManipulation/
├── BitwiseOperationsAndHacks.java  # Get/Set/Clear/Update Bit, Brian Kernighan, Power of Two, Lowbit
├── SingleNumberVariants.java       # Single Number I (XOR), Single Number II (Mod 3), Single Number III (2 distinct)
├── BitmaskAndCombinatorics.java    # Subsets via Bitmask, Counting Bits DP, Reverse Bits, Bitwise AND Range
└── README.md                       # Two's complement, bitwise identities, bitmask state compression
```

---

## 🧮 Essential Bitwise Identities
- **Check $k$-th bit**: `(n & (1 << k)) != 0`
- **Set $k$-th bit**: `n | (1 << k)`
- **Clear $k$-th bit**: `n & ~(1 << k)`
- **Toggle $k$-th bit**: `n ^ (1 << k)`
- **Isolate lowest set bit**: `n & (-n)`
- **Clear lowest set bit**: `n & (n - 1)` (Brian Kernighan's trick)
- **Power of Two check**: `n > 0 && (n & (n - 1)) == 0`

---

## 💡 Bitmask State Compression
When $N \le 20$, an integer variable of $N$ bits can model the status of $N$ items:
- Subset $\{0, 2, 3\}$ represented as $2^0 + 2^2 + 2^3 = 1 + 4 + 8 = 13$ (`0b1101`).
- Check if item $i$ in set: `(mask & (1 << i)) != 0`
- Add item $i$: `mask | (1 << i)`
- Remove item $i$: `mask & ~(1 << i)`
- Iterate over all sub-masks of `mask`:
  ```java
  for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
      // process sub-mask
  }
  ```

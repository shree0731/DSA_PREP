# 16-MathAndNumberTheory

Welcome to the **Mathematics & Number Theory** module of the Java DSA Preparation repository.

## Overview
Mathematics and Number Theory form the foundation of algorithm analysis, cryptography, and competitive programming.
This module covers prime generation, modular arithmetic, fast exponentiation, and combinatorics.

---

## Planned Directory Structure
```
16-MathAndNumberTheory/
├── PrimeNumbersAndSieve.java       # Sieve of Eratosthenes, Segmented Sieve, Prime Factorization
├── EuclideanGCDAndModular.java     # Extended Euclidean, Modular Exponentiation, Modular Inverse
├── CombinatoricsAndMatrixExp.java  # Matrix Exponentiation (O(log N) Fibonacci), nCr modulo P
└── README.md                       # Number theory formulas, modular inverse proofs, complexity table
```

---

## 🔢 Core Number Theory Theorems
1. **Fermat's Little Theorem**: If $p$ is prime and $\gcd(a, p) = 1$, then:
   $$a^{p - 1} \equiv 1 \pmod p \implies a^{p - 2} \equiv a^{-1} \pmod p$$
2. **Euclid's Algorithm**: $\gcd(a, b) = \gcd(b, a \pmod b)$ runs in $O(\log(\min(a, b)))$.
3. **Sieve of Eratosthenes**: Finds all primes up to $N$ in $O(N \log \log N)$ time.

---

## ⚡ Complexity Reference Matrix

| Algorithm | Problem | Time Complexity | Space Complexity |
|:---|:---|:---:|:---:|
| **Sieve of Eratosthenes** | Primes $\le N$ | $O(N \log \log N)$ | $O(N)$ |
| **Segmented Sieve** | Primes in $[L, R]$ | $O((R - L + 1) \log \log R)$ | $O(R - L + 1)$ |
| **Euclidean GCD** | $\gcd(a, b)$ | $O(\log(\min(a, b)))$ | $O(1)$ |
| **Matrix Exponentiation** | $N$-th Fibonacci | $O(\log N)$ | $O(1)$ |
| **Combinatorics $nCr$** | Query after precomputation | $O(1)$ | $O(N)$ |

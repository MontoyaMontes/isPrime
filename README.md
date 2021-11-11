## Algorithm that return if a given number is prime using Threads and Lambdas

## Info
Need Java 8 (need lambda implementation, can be rewritten for old versions using anonymous classes like compiler does).

### Use
Compile
```console
user@user:~/<container folder>$ javac Main.java
```
Now excecute

```console
user@user:~/<container folder>$ java Main
```
### Notes
In Main.java can be changed the value of **n** and number of threads.

## Theory

### Definition
>A prime is an **integer** that is greater than 1 and has no positive divisors other than 1 and itself.[[1]](#1)

### Algorithms and time complexity
The first approach to solving the problem of determining if a given number **n** is prime or not consist in create a loop and value *i=1* and answer if *i* divides **n**,
* if yes: Then **n** is not prime
* if not: Add 1 to *i*
And do this until *i*=**n**-1.

That is this algorithm use **O(n)** time

But that is not enough fast, due to if a number **n** is composite must have a prime divisot not exceeding the sauqre root of **n**[[2]](#2), so we can answer less times.

That makes time complexity **O(sqrt(n))**

Now, we can use threads and lambdas in Java 8 to make this "faster".

We create a **t** threads and array **A** of length **t**, and each thread will be responsible for check a segment of the number **n**, if some thread t<sub>k</sub> find a *m* value that is divisible by **n**, write false in **A[t<sub>k</sub>]**

### Example of thread
Lets have **n=50** and five threads (**t=5**) 

|  t<sub>1</sub>|  t<sub>1</sub>   |  t<sub>1</sub> |   t<sub>1</sub> |   t<sub>1</sub>|
| ------------ | ------------ | ------------ | ------------ | ------------ | 
| 1-10 |11-20 | 21-10 | 31-20 | 41-49 |

Every thread handle less numbers, but the time complexity still  **O(sqrt(n))**, but in real time is faster.


## References
<a id="1">[1]</a> 
Dudley, Underwood (1978).
"Section 2: Unique factorization".
Elementary number theory (2nd ed.). 
W.H. Freeman and Co. p. 10. ISBN 978-0-7167-0076-0.

<a id="2">[2]</a> 
[doc](http://gauss.math.luc.edu/greicius/Math201/Fall2012/Lectures/primes.article.pdf)
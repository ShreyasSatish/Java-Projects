# Inspiration
I decided to make this project repository after being introduced to Java in a module at University, and wanting to get better at it by applying it to things I am interested in

# Bioreactor
The inspiration for this was after learning about population growth models and the different ways you can approach it. I was curious to model them myself and explore other growth models

## Growth Models
### Exponential Growth
This is the most simple model, and probably the least accurate since it doesn't take into account any upper limit, competition, or death. The model can be written as follows:
```math
\begin{align*}
\frac{dN}{dt} &= rN \\
N(t) &= N_{0} e^{rt}
\end{align*}
```
Where $N$ is the population size and $r$ is the population growth rate.
### Logistic Growth
This is a better model than the Exponential Model as it has an upper limit to the population, but still does not account of competition or death. We can write it as follows:
```math
\begin{align*}
\frac{dN}{dt} &= rN - \frac{rN^2}{K} \\
N &= \frac{KN_{0}}{N_{0} + (K-N_{0} e^{-rt})}
\end{align*}
```

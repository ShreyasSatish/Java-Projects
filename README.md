# Inspiration
I decided to make this project repository after being introduced to Java in a module at University, and wanting to get better at it by applying it to things I am interested in

# Bioreactor
The inspiration for this was after learning about population growth models and the different ways you can approach it. I was curious to model them myself and explore other growth models

## Growth Models in this Project

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

### Monod Kinetics
This is more of a model to change the growth factor $r$ instead of modelling a population's growth. This can be applied to any model that has a population growth rate constant:
```math
r = r_{max} \frac{S}{K_{s} + S}
```
Where $r_{max}$ is the maximum growth rate, $S$ is the amount of some limited resource and $K_{s}$ is the value of $s$ where the growth rate is half the maximumm value. This would need to be used in tandem with a modelling method and an equation to model the decrease of the limited resource as a function of population.

### Allee Effect
This takes into account that the size of the population itself may affect the growth. This is an extension  of logistic growth. Depending on the strength of the Allee Effect, it will result in different population dynamics. For example, the strong effect causes the growth rate to be negative at small $N$ and can be modelled as follows:
```math
\frac{dN}{dt} = rN(1- \frac{N}{K})(\frac{N}{N_{c}} - 1)
```
Where $N_{c}$ is the critical population size (threshold) required for growth to occur. Note that this model has no exact solution.

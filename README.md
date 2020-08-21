# ASCII Tools
A set of tools for logging and command line output.

## Bar chart
A basic component for printing an ASCII bar chart. It's resonably configurable and well tested,
but probably doesn't cover many edge cases. It was designed for relatively simple scenarios.  
```kotlin
val data = mapOf("John" to 75, "Mary" to 23, "Alexander" to 34)
println(BarChart(data = data, minValue = -100, maxValue = 200))
```
will print
```text
             -100            0                          200
                ▼            ▼                          ▼
John                         │▇▇▇▇▇▇▇▇▇▇ 75
Mary                         │▇▇▇ 23
Alexander                    │▇▇▇▇ 34
```

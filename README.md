# ASCII Tools
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.com/blueanvil/ascii-tools.svg?branch=master)](https://travis-ci.com/blueanvil/ascii-tools)
[![Coverage Status](https://coveralls.io/repos/github/blueanvil/ascii-tools/badge.svg?branch=master)](https://coveralls.io/github/blueanvil/ascii-tools?branch=master)

A set of tools for logging and command line output.

## Bar chart
A basic component for printing an ASCII bar chart. It's resonably configurable and well tested,
but probably doesn't cover many edge cases. It was designed for relatively simple scenarios.  
```kotlin
val data = mapOf(
                "John" to 75,
                "Mary" to 23,
                "Alexander" to 34)
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


# License Information
The code is licensed under [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

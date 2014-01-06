# neural-network-currency

This Clojure project is for testing neural network ability to predict future currency value with input for NN currency values from past.

## Testing

Purpose of this application is to test ANN (Artificial Neural Network) ability to predict currency value in future. I will try predicting for some currencies with ANN and compare it with random generator to test it.

## Using

 - Historical currency converter web service http://currencies.apps.grandtrunk.net/ - for getting currency values
 - Neuroph 2.8 http://neuroph.sourceforge.net/download.html - for Neural Network

### Predictions

Historical currency converter doesn't update regularly for all currencies, and because of that, it will be tested for USD to EUR

Output for January 3. 2014.
```
Tomorrow: 0.7462855692305682
Day after tomorrow: 0.7439636342173588
Day after the day which is after tomorrow: 0.7660484304447854
```

```
Value for January 4. 2014: 0.733460466481
Value for January 5. 2014: 0.733460466481 (P.S. I can't believe it hadn't change either. I double checked it.)
Value for January 6. 2014: 0.735186002059
```

### Results

Average sum for prediction: 0.748399355573714733
Average sum for real value: 0.734035645007

Minimal difference between prediction and output: 0.0105031677363588
Maximal difference between prediction and output: 0.0308624283857854

Results: doesn't look promising.
 
## License

Copyright © 2013 Tihomir Radosavljević

Distributed under the Eclipse Public License, the same as Clojure.

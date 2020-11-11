
import sys
import pandas as pd
import pickle

try:
    # Taking Input Through Standard Input
    inputStockPriceFile = sys.stdin
    # Creating dataframe from standard input and transposing it
    inputStockPriceDF = pd.read_csv(inputStockPriceFile, header=None).transpose()
    # Providing name to the columns
    inputStockPriceDF.columns = {"Open", "High", "Low", "Volume"}
    # Loading the dumped LinearRegressionModel pickle file
    with open("./app/MachineLearningModel/PythonModel/StockPriceModel.pkl", "rb") as modelFile:
        linearRegressionModel = pickle.load(modelFile)

    # Predicting the stock close price
    predictedClosePrice = linearRegressionModel.predict(inputStockPriceDF)
    # Returning the output through standard output
    sys.stdout.write(str(predictedClosePrice[0]))


except FileNotFoundError:
    sys.stdout.write(str(-sys.maxsize))
    print("The pickle file doesnot exist")
except ex:
    sys.stdout.write(str(-sys.maxsize))
    print(ex,"\n Something Unexpected Occured")

# MNIST Digit Predictor README

This program aims to fufill 2 Use Cases:


1. User uploads an image of a handwritten digit for recognition

2. User draws a digit using mouse inside the canvas area

It will then predict the digit uploaded or drawn in the Canvas Area after you press the Predict button. 




To run you must clone the repo into your IDE of choice and then run it through the compliler.


The project has no further requirements to make it run.


The program has some limitations in regards to its accuracy. 



#Usage

To upload an image simply press the 'Upload Image' button. This will take you to a file system where you can select an image to upload to the central box in the UI.
From here you can click Predict Digit to have the program attempt to predict the digit in the uploaded image.
You can also draw your own image in the left most black panel, the Canvas. To do this simply click and drag your mouse in the area. You can then press the Save image button below to transfer your drawing to the middle upload panel.
From hear follow the same step as the first use case to get a prediction. There is also a 'Clear Drawing' button if you wish to clear the Canvas and start your drawing again.



The accuracy of predictions of MNIST Test Data that you can upload through the upload Button if you have the images is very accurate, with most predictions being correct and 100% confident.


The Drawn images however are often incorrect with the confidence also being fairly low when they are correct.



The Program also takes a few minutes to complete a prediction due to the 60000 images it has to process and will not be responsive during this time.

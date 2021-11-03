Tempo Task Sample Android App Using newsapi
===========================================

This is a sample Android App that uses the News api (https://newsapi.org/?ref=apilist.fun)
to list updated news articles

## Building the Sample App

First, clone the repo:
 git clone https://github.com/beshoyfakhry/TempoTask.git

The key of the api can be found in apikey.properties file

Building the sample then depends on your build tools.


## Running the Sample App

Connect an Android device to your development machine , or use an emulator.
should support api 26 and more

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

## Api Data
 sortBy:publishedAt
 from  :2021-10-10  
 
 This specific from date as we are using a free version of the api so we can not retrieve from old date.

## Using the Sample App

Using this sample app user can search for the news articles sorted by publishBy filed,
user will search with search key.

After retreving the data you can check the items viewed by count of 20 item per page,
When click on any item you can go for the item details.
Item details show image in larger scal and the news article details.
User can click on @ button in order to go for the source of the news.

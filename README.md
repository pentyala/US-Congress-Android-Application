# US-Congress-Android-Application
An Android application that displays the data of US Congress. It shows the information of legislators, bills and committees. Searching can also be done along with a index.

# Intro:
When the application is launched, by default the legislators tab is loaded. Legislators are divided into three categories. Total legislators are 538 in number.
  1.	Listed by state (Sorted with respect to their states)
  2.	All legislators by house are listed.
  3.	All legislators by senate are listed.
Each tab has a fast scroll menu where a user can directly click on an index and the legislators are scrolled to that indexed letter. By default the indexing is done for states and legislators last name.
# Details:
When the arrow button is clicked on a legislator, his details are loaded. 
Details include his photo, the start date of his term and his end date, progress of his term along with contact information.
There are links on top of his photo where a user can visit the legislator’s Facebook, Twitter and his personal website.
There is an option on to the top right of the page where a user can mark that legislator as a favorite.

There is a slide in-out menu where a user can navigate among the pages of Legislators, Bills, Committees and Favorites.
Bills are divided into two major categories Active Bills i.e. bills that are active and New Bills i.e. bills that are not yet accepted in the congress. There are a lot of bills almost 4,000 in number which is very difficult for me to display them. Instead I fetched the top 50 bills and displayed them respectively.

In the detail section of bills we have the name of the bill and its title. It also has details such as who the sponsored the bill and the date of induction along with url to its PDF document.
There is also an option to mark the bill as a favorite.
Committees are divided into three categories into House, Senate and Joint. As the names indicate the House committees are the committees that are organized by the house legislators. Similarly the Senate and Joint committees. In the details section it displays the id, Name of the committee and an option to mark it as a favorite.
In the Favorites as displayed, there are three tabs where each tab contains the favorite Legislators, Committees and Bills respectively.

# Development:

All the data is fetched from the Sun Light Congress API using the Rest Calls. This data is very huge in quantity, so I have used a server to remove the unnecessary fields keeping only the needed information.
All the favorites are stored using SQLite Database.
In order to display the list of data I have used the ListView component and populated with the data. I have also used an adapter for each section which made the development easier and quicker.

In order to display the high resolution pictures without putting too much load on the main thread, I have used the Picasso plugin which helps fast loading of images and reduces the amount of load on the main thread. Picasso also helps in caching the images which is very useful in this case as all the images doesn’t change often.
Data is fetched in the form of JSON which is parsed into an object with the help of GSON plugin which converts a JSON object into a user defined class.

# Performance:

The application runs very smooth on the latest version of Android (7.1). Due to the reason it is developed on Nougat version which is not yet released on mobiles but only for development, I could only test this on Emulator. 
Even though the emulator takes high memory the application itself was quick and efficient.

# References:
## Picasso - http://square.github.io/picasso/
## Gson - https://github.com/google/gson

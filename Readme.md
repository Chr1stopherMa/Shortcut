# Shortcut
 
## Description

Shortcut is a mobile application that allows seniors and others who are new to technology to learn and use popular mobile applications. Seniors often have issues learning and using new technology and they need a product that simplifies this task. This is especially important now during the COVID-19 pandemic, as social media and entertainment applications often provide them with their only means of communication.
 
Organizations such as Family Services Toronto are unable to provide live workshops, as they have in the past, in order to teach novice users how to use modern technology. Therefore, we’ve created an application which provides short tutorials that cover the basic functionalities of the most frequently used applications amongst seniors: WhatsApp, Google, YouTube, Zoom, Email and Facebook (as provided by Family Services Toronto) .
 
In addition, we have also simplified the user interface by listing out these vital apps on a single screen as the homepage. Simply adding the Shortcut app on their device’s homepage allows them to easily navigate to different applications by tapping the appropriate icon instead of shifting through multiple screens on their phone to find an app. In essence, we hope we’ve developed a product that matches everyday functionality with an intuitive and senior-friendly interface.
 
## Key Features

The purpose of Shortcut is two-fold: first to provide a single location to access common mobile applications, and second to provide simple tutorials on how to use some of the functionalities of these aforementioned applications. Simplicity and ease-of-use were the key factors considered when determining the scope of the application.
 
In order to provide the ability to access common mobile applications in one singular location, we’ve created a homepage that contains the six most used applications by seniors (as provided by Family Services Toronto). These are Youtube, Zoom, Facebook, Whatsapp, Google, and Gmail. Their logos along with the name of the application can be seen right after you open the Shortcut app. This allows seniors to merely have Shortcut as a singular app on their mobile device’s homepage and they can easily access these six apps without the need to scroll or search their devices to find the given apps. Simply clicking the app they want to go to and clicking “Go To ____” will allow them to go to said app.
 
We have also provided simple tutorials for each of the aforementioned apps. As seniors are unable to attend their regular workshops and help sessions to learn some of the basic functions of these common apps, we’ve provided step-by-step instructions on how to achieve these functionalities. These instructions include pictures/images from the apps, and concise and effective statements to help novice users navigate through the functionalities of these common applications.
 
## Instructions

As outlined above, the goal of Shortcut is to provide one application to contain access to some of the most used apps and provide concise instructions for novice technology users to follow. There is no need for a new user to create an account or require any additional steps to begin using the application. Since the purpose of Shortcut is to provide users who are new to mobile technology with a simple platform to view and learn about popular applications, it would be counterintuitive to require them to do more work with technology. Once you open the application on your Android phone, you are brought to the homepage which contains the logos of Youtube, Zoom, Facebook, Whatsapp, Google, and Gmail. Tap on the app’s logo you’d like to find tutorials on or to use, it will then take you to the app’s tutorial page.
 
For example, tapping on the Facebook icon takes you to a page titled “Facebook Tutorials”. Below you have access to three buttons which contain tutorials on how to “Add a Friend”, “Upload Picture/Video”, and “Edit Profile”. If, for instance, you’d like to learn how to upload pictures onto your facebook account, you would tap the “Upload Picture/Video” button which would then take you to a page with step by step instructions and images on how to upload pictures onto your facebook account. You can then click “Back” to go back to the Facebook Tutorials page. Now that you know how to add pictures to your Facebook profile, why not try it out? Tapping “Go To Facebook” will open up the Facebook app on your phone. If you don’t have Facebook installed on your device, it will take you to the app’s page on the Play Store so you can install it! Clicking “Back” from the “Facebook Tutorials” page takes you back to the homepage so you can explore a different listed app. All the apps on the Home Page have similar tutorials and links to open their relevant applications.
 
## Development requirements

Our app is readily available for download through the [Google Play Store](https://play.google.com/store/apps/details?id=com.familyservicestoronto.shortcut). You may search up “Shortcut” on the Google Play Store and click “Install” to run it on your mobile device. Once the device is installed, tapping the Shortcut icon on your device will run the application.
 
If a developer were to review our source code or make changes/edit they could do so through Android Studio which is the application that we used for development. They could see the source code (available in our private Github repository) and edit the Java class and xml files through Android Studio. They can go to the AVD manager to pick the virtual device that you would like to work with; different devices may have different resolution and OS. Android Studio also allows them to run the current version of the application on an emulator or on their personal device so they can easily monitor the effects of their changes.
 
## Deployment and Github Workflow

In order to avoid conflict, some of us created new branches for our own part and worked on that specific branch and merged them back to the main branch afterwards. Task managers reviewed the pull-request and sent out communications in our Facebook group chat if any merge conflicts were spotted.  All group members followed the camelcase naming convention to ensure consistency. We also adhered to the Single Responsibility Principle with each unique application and its unique functionalities assigned to separate java classes and xml files. A member of our team was assigned to each of these files as well and in charge of it so they would monitor all required updates and changes which we would communicate through or chat or meetings.This further avoided the rise of conflicts. It also allowed any team members who were involved in conflicts to arrange a discussion session within themselves to solve the merging issue.
 
In order to have Continuous Integration we are planning to use Fastlane as our deployment tool. We simply need to install the prerequisites required for the fastlane process (as seen here: https://docs.fastlane.tools/getting-started/android/setup/) and finally can deploy it to the Google App Store using their deployment features (as seen here https://docs.fastlane.tools/getting-started/android/release-deployment/). Fastlane will also allow us to automate the deployment process and have our various builds to follow automatically integrated as well.
 
## Licenses

We’ve applied an MIT license to our codebase. The only limitations this applies are that the copyright and license notices must be included when the code is used. This means that others may contribute to our project or can use our code in their projects as long as they include the license and copyright.
 
Our partner strongly recommended the project to be open source as he wanted continuous integration of more tutorials and applications added on as he receives more feedback from the Family Services Toronto community. The MIT license was the simplest and most effective license that accomplished this.

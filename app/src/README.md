
# Medication Alarm/Reminder Application


Overview
This application is designed to help users manage their medication schedules by providing reminders and alarms. Users can register, log in, and set medication alarms. The application uses SQLite3 as its local database to store user information and medication schedules.
Features
User Registration: Allows users to create an account with their name, age, CNIC number, disease name, and password.
User Login: Users can log in using their CNIC number and password.
Medication Reminders: Set up reminders for taking medication.
Alarm Notifications: Get notifications for upcoming medication times.
Database Schema
The application uses SQLite3 to manage the local database. Below is the schema for the database:



## Users Table
Column	Type	Description
id	INTEGER	Primary key, auto-increment
name	TEXT	User's full name
age	INTEGER	User's age
cnic_number	TEXT	CNIC number (unique)
disease_name	TEXT	Disease the user is affected by
password	TEXT	User's password (hashed)
## Setup and Installation
Clone the Repository

git clone <https://github.com/danialafridi1/Android-Based-Medication-Alarm--Sqlite-Base.git>
cd medication-alarm-app
Open in Android Studio

Open Android Studio and select "Open an existing project."
Navigate to the project directory and open it.
Build and Run

Click on "Run" in Android Studio to build and run the application on an emulator or a connected device.
Configuration
Database Initialization

The database is initialized when the application is first run. The SQLiteOpenHelper class manages the creation and versioning of the database.

Permissions

Make sure to request the necessary permissions for notifications and any other features you may use.

## Usage
# Register

Navigate to the registration page.
Enter your name, age, CNIC number, disease name, and password.
Click "Register" to create your account.
# Login

Navigate to the login page.
Enter your CNIC number and password.
Click "Login" to access your account.

## Contributing
Fork the repository
Create a new branch (git checkout -b feature-branch)
Commit your changes (git commit -am 'Add new feature')
Push to the branch (git push origin feature-branch)
Create a new Pull Request
## Contact
For any questions or issues, please contact [iamdanialafridi@gmail.com].


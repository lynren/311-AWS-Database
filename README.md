# AWS Database
This package contains the code for a MySql database hosted on AWS, and accessed using AWS Lambda.

## Files & Directories

### Project Information

- **context_diagram.jpg**: a context diagram for the android app. This represents the team's initial thoughts on how the app's data flow should work.
- **Project Part 2.pdf**: The _Design & Implementation_ document for the android app. This goes through the _architectural_, _static_, and _dynamic_ views of the design. It also contains _user stories_ and the _technology stack_ which we planned on using during this stage of development. The tech stack in this document does not accurately reflect the tech stack used as we had to adapt to different obstacles and needs.
- **Project Part 3.pdf**: Document reviewing our development process at the end of the project. 

### Code

- **MemeDB.java**: _Iterator_ which retrieves image url strings from the database upon initialization. This program connects to an AWS Lambda function, which can be found in the _meme_ directory.
- **MemeDBnoapi.java**: _Iterator_. Performs the same functions as _MemeDB.java_, but without a Lambda API. This requires the database password to be distrubuted to every user, which is not secure. This file was only used for testing before I learned Lambda.
- **memedb_schema.sql**: MySql script which creates the _meme_ database.
- **memedb_values.sql**: MySql script which populates the _meme_ database with values.
- **meme**: Maven project containing the AWS Lambda program for database retreival. 

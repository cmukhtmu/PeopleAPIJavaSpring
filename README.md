## Setup

1. Clone the repo
2. Copy `.env.example` to `.env` and update with your database credentials
    2.1. The API needs a PostGres database will a table named People.
    2.2. People table must have the following fields:
        ID
        First_Name
        Last_Name
        Phone
    For more details like data types etc. please see model classes.
3. Run: `docker-compose up`
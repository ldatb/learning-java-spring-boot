CREATE TABLE IF NOT EXISTS TB_PARKING_SPOT
(
    id                  UUID PRIMARY KEY,
    parking_spot_number INTEGER     NOT NULL UNIQUE,
    car_license_plate   VARCHAR(7)  NOT NULL UNIQUE,
    car_brand           VARCHAR(30) NOT NULL,
    car_model           VARCHAR(30) NOT NULL,
    car_color           VARCHAR(10) NOT NULL,
    registration_date TIMESTAMP NOT NULL
);

TRUNCATE TABLE rental_info;
TRUNCATE TABLE app_user;
TRUNCATE TABLE video;
TRUNCATE TABLE video_type;

TRUNCATE TABLE rental_info restart identity;
TRUNCATE TABLE app_user restart identity;
TRUNCATE TABLE video restart identity;
TRUNCATE TABLE video_type restart identity;
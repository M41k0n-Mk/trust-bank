DO $$ BEGIN IF NOT EXISTS (
    SELECT

    FROM
        pg_database
    WHERE
        datname = 'trust_bank'
) THEN CREATE DATABASE trust_bank;
END IF;
END $$;
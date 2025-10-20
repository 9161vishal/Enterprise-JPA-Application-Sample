-- Drop 'state' column from users if it exists
DO $$
BEGIN
  IF EXISTS (
    SELECT 1 FROM information_schema.columns 
    WHERE table_name = 'users' AND column_name = 'state'
  ) THEN
    ALTER TABLE public.users DROP COLUMN state;
  END IF;
END$$;

-- Add 'state' column to addresses if it doesn't exist
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM information_schema.columns 
    WHERE table_name = 'addresses' AND column_name = 'state'
  ) THEN
    ALTER TABLE public.addresses ADD COLUMN state CHAR(1) COLLATE pg_catalog."default";
  END IF;
END$$;

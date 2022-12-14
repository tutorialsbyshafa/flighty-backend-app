CREATE TABLE public.booking
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    external_id        UUID      DEFAULT uuid_generate_v1(),
    creation_date_time TIMESTAMP DEFAULT NOW() NOT NULL,
    active             BOOLEAN   DEFAULT TRUE  NOT NULL,
    user_id            BIGINT REFERENCES public.app_user (id),
    flight_id          BIGINT REFERENCES public.flight (id)
);
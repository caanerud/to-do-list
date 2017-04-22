--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: Priorities; Type: TABLE; Schema: public; Owner: chrisaanerud
--

CREATE TABLE "Priorities" (
    id integer NOT NULL,
    name character varying(200) NOT NULL
);


ALTER TABLE "Priorities" OWNER TO chrisaanerud;

--
-- Name: Tasks; Type: TABLE; Schema: public; Owner: chrisaanerud
--

CREATE TABLE "Tasks" (
    id integer NOT NULL,
    completed integer DEFAULT 0 NOT NULL,
    duedate date,
    priority integer DEFAULT 0 NOT NULL,
    taskname character varying(250),
    details character varying(400),
    "user" integer NOT NULL
);


ALTER TABLE "Tasks" OWNER TO chrisaanerud;

--
-- Name: Users; Type: TABLE; Schema: public; Owner: chrisaanerud
--

CREATE TABLE "Users" (
    id integer NOT NULL,
    name character varying(200) NOT NULL,
    password character varying(200) NOT NULL
);


ALTER TABLE "Users" OWNER TO chrisaanerud;

--
-- Data for Name: Priorities; Type: TABLE DATA; Schema: public; Owner: chrisaanerud
--

COPY "Priorities" (id, name) FROM stdin;
1	Low
2	Normal
3	High
\.


--
-- Data for Name: Tasks; Type: TABLE DATA; Schema: public; Owner: chrisaanerud
--

COPY "Tasks" (id, completed, duedate, priority, taskname, details, "user") FROM stdin;
1	0	2017-04-18	0	Learn Java	Learn a new programming language	1
\.


--
-- Data for Name: Users; Type: TABLE DATA; Schema: public; Owner: chrisaanerud
--

COPY "Users" (id, name, password) FROM stdin;
2	Robert	dog
1	Chris	cat
\.


--
-- Name: Priorities Priorities_pkey; Type: CONSTRAINT; Schema: public; Owner: chrisaanerud
--

ALTER TABLE ONLY "Priorities"
    ADD CONSTRAINT "Priorities_pkey" PRIMARY KEY (id);


--
-- Name: Tasks Tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: chrisaanerud
--

ALTER TABLE ONLY "Tasks"
    ADD CONSTRAINT "Tasks_pkey" PRIMARY KEY (id);


--
-- Name: Users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: chrisaanerud
--

ALTER TABLE ONLY "Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


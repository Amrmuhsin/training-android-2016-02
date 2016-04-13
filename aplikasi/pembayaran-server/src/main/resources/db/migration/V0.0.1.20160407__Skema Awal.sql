--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

--
-- Name: m_produk; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_produk (
    id character varying(255) NOT NULL,
    kode character varying(255) NOT NULL,
    nama character varying(255) NOT NULL
);


ALTER TABLE m_produk OWNER TO pembayaran;

--
-- Name: m_user; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_user (
    id character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    fullname character varying(255) NOT NULL,
    nomer_handphone character varying(255) NOT NULL
);


ALTER TABLE m_user OWNER TO pembayaran;

--
-- Name: m_user_password; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE m_user_password (
    id character varying(255) NOT NULL,
    hashed_password character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL
);


ALTER TABLE m_user_password OWNER TO pembayaran;

--
-- Name: t_pembayaran; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_pembayaran (
    id character varying(255) NOT NULL,
    nilai numeric(19,2) NOT NULL,
    waktu_transaksi timestamp without time zone NOT NULL,
    id_user character varying(255) NOT NULL,
    id_tagihan character varying(255) NOT NULL,
    CONSTRAINT t_pembayaran_nilai_check CHECK ((nilai >= (0)::numeric))
);


ALTER TABLE t_pembayaran OWNER TO pembayaran;

--
-- Name: t_saldo; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_saldo (
    id character varying(255) NOT NULL,
    nilai numeric(19,2) NOT NULL,
    id_user character varying(255) NOT NULL,
    CONSTRAINT t_saldo_nilai_check CHECK ((nilai >= (0)::numeric))
);


ALTER TABLE t_saldo OWNER TO pembayaran;

--
-- Name: t_tagihan; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_tagihan (
    id character varying(255) NOT NULL,
    admin numeric(19,2) NOT NULL,
    bulan_tagihan date NOT NULL,
    denda numeric(19,2) NOT NULL,
    jatuh_tempo date NOT NULL,
    lain_lain numeric(19,2) NOT NULL,
    lunas boolean NOT NULL,
    nilai numeric(19,2) NOT NULL,
    pajak numeric(19,2) NOT NULL,
    id_produk character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL,
    CONSTRAINT t_tagihan_admin_check CHECK ((admin >= (0)::numeric)),
    CONSTRAINT t_tagihan_denda_check CHECK ((denda >= (0)::numeric)),
    CONSTRAINT t_tagihan_lain_lain_check CHECK ((lain_lain >= (0)::numeric)),
    CONSTRAINT t_tagihan_nilai_check CHECK ((nilai >= (0)::numeric)),
    CONSTRAINT t_tagihan_pajak_check CHECK ((pajak >= (0)::numeric))
);


ALTER TABLE t_tagihan OWNER TO pembayaran;

--
-- Name: t_tagihan_favorit; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_tagihan_favorit (
    id character varying(255) NOT NULL,
    nama_pelanggan character varying(255),
    nomer_pelanggan character varying(255) NOT NULL,
    id_produk character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL
);


ALTER TABLE t_tagihan_favorit OWNER TO pembayaran;

--
-- Name: t_user_gcm_token; Type: TABLE; Schema: public; Owner: pembayaran
--

CREATE TABLE t_user_gcm_token (
    id character varying(255) NOT NULL,
    gcm_token character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL
);


ALTER TABLE t_user_gcm_token OWNER TO pembayaran;

--
-- Name: m_produk_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_produk
    ADD CONSTRAINT m_produk_pkey PRIMARY KEY (id);


--
-- Name: m_user_password_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user_password
    ADD CONSTRAINT m_user_password_pkey PRIMARY KEY (id);


--
-- Name: m_user_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user
    ADD CONSTRAINT m_user_pkey PRIMARY KEY (id);


--
-- Name: t_pembayaran_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_pembayaran
    ADD CONSTRAINT t_pembayaran_pkey PRIMARY KEY (id);


--
-- Name: t_saldo_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_saldo
    ADD CONSTRAINT t_saldo_pkey PRIMARY KEY (id);


--
-- Name: t_tagihan_favorit_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan_favorit
    ADD CONSTRAINT t_tagihan_favorit_pkey PRIMARY KEY (id);


--
-- Name: t_tagihan_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan
    ADD CONSTRAINT t_tagihan_pkey PRIMARY KEY (id);


--
-- Name: t_user_gcm_token_pkey; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_user_gcm_token
    ADD CONSTRAINT t_user_gcm_token_pkey PRIMARY KEY (id);


--
-- Name: uk_9ev77sfyb2omfjx1ugrdcgftm; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_produk
    ADD CONSTRAINT uk_9ev77sfyb2omfjx1ugrdcgftm UNIQUE (kode);


--
-- Name: uk_fonymmte14l9gqus81nka8kug; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_produk
    ADD CONSTRAINT uk_fonymmte14l9gqus81nka8kug UNIQUE (nama);


--
-- Name: uk_rycw44p7cruupkosx3ibmj9q3; Type: CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user
    ADD CONSTRAINT uk_rycw44p7cruupkosx3ibmj9q3 UNIQUE (email);


--
-- Name: fk_1gho76p3grval4so0p5kyebib; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_pembayaran
    ADD CONSTRAINT fk_1gho76p3grval4so0p5kyebib FOREIGN KEY (id_tagihan) REFERENCES t_tagihan(id);


--
-- Name: fk_5761xl2u1oawuyw71vhm2vk2a; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan_favorit
    ADD CONSTRAINT fk_5761xl2u1oawuyw71vhm2vk2a FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_eedqilas8ubt8l9eppr86fx1m; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan_favorit
    ADD CONSTRAINT fk_eedqilas8ubt8l9eppr86fx1m FOREIGN KEY (id_produk) REFERENCES m_produk(id);


--
-- Name: fk_gpucotvh1ouym2r58ydd9dsw9; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_user_gcm_token
    ADD CONSTRAINT fk_gpucotvh1ouym2r58ydd9dsw9 FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_gq8pvjcos5d8wk29m8jn3425j; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan
    ADD CONSTRAINT fk_gq8pvjcos5d8wk29m8jn3425j FOREIGN KEY (id_produk) REFERENCES m_produk(id);


--
-- Name: fk_hb4hywdjo9cak0vs22qttd1uk; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_pembayaran
    ADD CONSTRAINT fk_hb4hywdjo9cak0vs22qttd1uk FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_m2a93fmrjgvvkowobo3lixd39; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY m_user_password
    ADD CONSTRAINT fk_m2a93fmrjgvvkowobo3lixd39 FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_qssbigmpix7dl6fe9c2tbtjlh; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_tagihan
    ADD CONSTRAINT fk_qssbigmpix7dl6fe9c2tbtjlh FOREIGN KEY (id_user) REFERENCES m_user(id);


--
-- Name: fk_rnneahhjra073iyns4i3xts1x; Type: FK CONSTRAINT; Schema: public; Owner: pembayaran
--

ALTER TABLE ONLY t_saldo
    ADD CONSTRAINT fk_rnneahhjra073iyns4i3xts1x FOREIGN KEY (id_user) REFERENCES m_user(id);



--
-- PostgreSQL database dump complete
--


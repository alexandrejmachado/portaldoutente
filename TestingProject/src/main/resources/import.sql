--
-- JBoss, Home of Professional Open Source
-- Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- This script initializes the database by creating tables and inserting data
-- Use only for a proof of concept

-- You can use this file to load seed data into the database using SQL statements
INSERT INTO `portalDoUtente`.`Utente` (`numUtente`, `cc`, `contactoEmergencia`, `dataNascimento`, `email`, `morada`, `nif`, `nome`, `password`, `telemovel`) VALUES ('123456789', '123456789', '123456789', '2016-04-14 11:21:09.0', 'asdasd@asdasd.pt', 'sfsdfsdfsdf', '123456789', 'Conta de Testes', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', '123456789');



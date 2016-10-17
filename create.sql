CREATE TABLE "at_api_info" (
	 "id" INTEGER NOT NULL,
	 "project_id" INTEGER NOT NULL,
	 "api_name" TEXT NOT NULL,
	 "api_uri" TEXT NOT NULL,
	 "api_method" integer NOT NULL,
	 "ctime" timestamp,
	 "remark" TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "at_project" (
	 "id" INTEGER NOT NULL,
	 "name" TEXT NOT NULL,
	 "domain" TEXT NOT NULL,
	 "port" integer NOT NULL,
	 "ctime" datestamp,
	 "remark" TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "at_headers" (
	 "id" INTEGER NOT NULL,
	 "api_id" INTEGER NOT NULL,
	 "name" TEXT NOT NULL,
	 "value" TEXT NOT NULL,
	 "explain" TEXT,
	 "ctime" datestamp,
	 "remark" TEXT,
	PRIMARY KEY("id")
)

CREATE TABLE "at_params" (
	 "id" INTEGER NOT NULL,
	 "api_id" INTEGER NOT NULL,
	 "name" TEXT NOT NULL,
	 "value" TEXT NOT NULL,
	 "explain" TEXT,
	 "ctime" datestamp,
	 "remark" TEXT,
	PRIMARY KEY("id")
)
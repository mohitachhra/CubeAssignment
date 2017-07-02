create table Event(
 	id number,
  	userid Number ,
	ts varchar(20),
	noun varchar(20),
	verb varchar(20),
	latlong varchar(20),
	timespent Number,
  	properties varchar2(1000),
  	creationTime Timestamp
  	);
 
create table Properties (
 	pid number,
 	lhs varchar(50),
 	rhs varchar(50),
 	eventId number
 	);
 
create table rule(       
	action varchar(20),
    ruleId number,
	query varchar(500)
    );
       
create table ActionDefinition(
  	aDefId int,
	query CLOB,
	executeAfter int,
  	activity varchar(50),
  	queryParams Varchar(50)
 	);
  
create table Action(
  	actionId number,
	actionDefId number,
	query varchar(500),
	triggeringEventId number,
	triggeringUserId number,
	pickUpTime timestamp,
	creationTime timestamp
  );
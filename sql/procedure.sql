DELIMITER $$
create PROCEDURE Load5Year(  
    etime datetime,
    stime datetime,
    cname VARCHAR(45),
    cprice FLOAT,
    cquantity Integer
)

BEGIN
DECLARE squantity Integer DEFAULT 0;
set squantity = cquantity;
WHILE stime <= etime DO
	
	IF stime + interval '1' year < etime THEN 

			INSERT INTO stock (name,price,time,quantity)
						VALUES
						(cname, cprice, stime,cquantity);
			set cprice = cprice + cprice*(0.001)*power(-1,round(RAND()));
            set stime = stime + interval '1' month;
			set cquantity = cquantity + round(10*(rand()))*power(-1,round(RAND()));
			If cquantity < 0 then
				set cquantity = 0;
			elseif cquantity > squantity then
				set cquantity = squantity;
			end if;
            
	elseif stime + interval '1' year >= etime and month(stime) < month(etime) then
				if weekday(stime) = 0 or week(now()) -1 = week(stime)then
					INSERT INTO stock (name,price,time,quantity)
								VALUES
								(cname, cprice, stime,cquantity);
				end if;
                
				set cprice = cprice + cprice*(0.001)*power(-1,round(RAND()));
                set stime = stime + interval '1' day;
				set cquantity = cquantity + round(10*(rand()))*power(-1,round(RAND()));
				If cquantity < 0 then
					set cquantity = 0;
				elseif cquantity > squantity then
					set cquantity = squantity;
				end if;
     elseif stime + interval '1' year >= etime and month(stime) = month(stime) and day(stime) < day(etime) then
		
			INSERT INTO stock (name,price,time,quantity)
						VALUES
						(cname, cprice, stime,cquantity);
			set cprice = cprice + cprice*(0.001)*power(-1,round(RAND()));
            set stime = stime + interval '1' day;
			set cquantity = cquantity + round(10*(rand()))*power(-1,round(RAND()));
			If cquantity < 0 then
				set cquantity = 0;
			elseif cquantity > squantity then
				set cquantity = squantity;
			end if;
            
	     else
			if weekday(stime) < 5 then
				INSERT INTO stock (name,price,time,quantity)
						VALUES
						(cname, cprice, stime,cquantity);
			end if;
			set cprice = cprice + cprice*(0.001)*power(-1,round(RAND()));
            set stime = stime + interval '60' second;
			set cquantity = cquantity + round(10*(rand()))*power(-1,round(RAND()));
			If cquantity < 0 then
				set cquantity = 0;
			elseif cquantity > squantity then
				set cquantity = squantity;
			end if;
    end if;
end while;
END$$ 
DELIMITER ;
CALL Load5Year(now(),'2015-01-01 08:00:00','sb1', 100.0, 100);

DROP PROCEDURE Load5Year;
SET SQL_SAFE_UPDATES = 0;
Delete from stock;
drop table stock;



select s.*
from stock s
inner join (
    select name, max(time) as MaxDate
    from stock
    group by name
) tm on s.name = tm.name and s.time = tm.MaxDate order by name;

#select this day of record date
select *
from stock s
where name = 'sb1' and day(now()) = day(time) and year(now()) = year(time) and month(time) = month(now());

# select this week of record date
select s.*
from stock s
where name = 'sb1' and weekday(time) < 5 and week(now()) = week(time)
and year(now()) = year(time) and time(time) = '08:00:00';

#select last week of record date 
select s.*
from stock s
where name = 'sb1' and week(now())-1 = week(time) and weekday(time) < 5 
and  year(now()) = year(time);

#select this month of record date
select s.*
from stock s 
where name = 'sb1' and month(now()) = month(time) and weekday(time) < 5 
and year(now()) = year(time) and time(time) = '08:00:00';

#select this year of record week
select s.*
from stock s
where name = 'sb1' and weekday(time) < 5 and weekday(time) = 0
and year(now()) = year(time) and time(time) = '08:00:00'; 

#select last 5 year of record week
select s.*
from stock s
where name = 'sb1' and month(time) = month(now()) and day(time) = 1; 


CALL Load5Year(now(),'2015-01-01 08:00:00','YWA', 100.0, 100);
CALL Load5Year(now(),'2015-01-01 08:00:00','LDT', 100.0, 100);
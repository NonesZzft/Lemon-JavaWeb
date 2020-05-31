SET GLOBAL event_scheduler = ON;


CREATE EVENT autoupdate
    ON SCHEDULE EVERY 30 SECOND
    DO
      INSERT INTO stock (name,price,time,quantity)
						VALUES
						("sb1", 100 + round(100*(rand()))*(0.01)*power(-1,round(RAND())),now(),round(100*(rand())));
                        
ALTER EVENT autoupdate DISABLE;
drop event autoupdate;
                        
DELIMITER $$
 Create event setschedule
	ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 15 second
    Do begin
		update user 
        set balance = balance - 3*(select price from stock s where s.name = 'A' and s.time = (SELECT MAX(s2.time) 
								FROM stock s2 WHERE s2.name = 'A'))
		where user.name ='user';
		
        update buy
        set quantity = quantity +3
        where u_name = 'user' and s_name = 'A';
END$$
DELIMITER ;


ALTER EVENT autoupdate DISABLE;
drop event autoupdate;
SET GLOBAL event_scheduler = OFF;


Delete from stock;
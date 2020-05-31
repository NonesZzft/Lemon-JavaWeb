 Create event setschedule1
	ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 15 second
    Do
		update user 
        set balance = balance - 3*(select price from stock s where s.name = 'A' and s.time = (SELECT MAX(s2.time) 
								FROM stock s2 WHERE s2.name = 'A'))
		where user.name ='user';
        
Create event setschedule2
	ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 15 second
    Do
        update buy
        set quantity = quantity +3
        where u_name = 'user' and s_name = 'A';
        

        
SHOW EVENTS FROM stock;
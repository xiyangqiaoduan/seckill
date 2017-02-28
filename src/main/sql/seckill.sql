-- 存储过程的创建  存储过程

create procedure execute_seckill(
	in v_seckill_id bigint,in v_phone bigint,in v_kill_time timestamp ,out r_result int 
)
-- ----	ROW_COUNT 返回影响的行数信息
begin 
	declare insert_count INT  default 0;
	start  transaction ;
	-- 插入秒杀信息
	INSERT IGNORE INTO success_killed ( seckill_id,user_phone,create_time) VALUES (v_seckill_id,v_phone,v_kill_time) ;
	-- 获取影响行数
	SELECT ROW_COUNT() INTO insert_count;
	IF (insert_count=0) THEN 
	ROLLBACK;
	SET r_result=-1;
	ELSEIF (insert_count<0) THEN
	ROLLBACK;
	SET r_result=-2;
	ELSE 
		update seckill SET number=number-1 WHERE  seckill_id=v_seckill_id  AND  number>0 AND start_time< v_kill_time  AND end_time > v_kill_time ;
		SELECT ROW_COUNT() INTO insert_count;
		IF  (insert_count=0) THEN 
			ROLLBACK;
			SET r_result=-1;
				
		ELSEIF (insert_count<0)   THEN
			ROLLBACK;
			SET r_result=-2;
		ELSE
			COMMIT;
			SET r_result=1;
		end IF;
	end IF;
end ;



-- 测试存储过程
SET @r_result=-3;
call execute_seckill(1001,15165732356,NOW(),@r_result);
SELECT @r_result;

SELECT

	CLAS_S.SEMESTER,
	"time",
	COURSE.COURSE_NAME
FROM
	PUBLIC.CLASS CLAS_S
	INNER JOIN PUBLIC.COURSE COURSE ON CLAS_S.CLASS_COURSE_ID = COURSE.ID
	INNER JOIN PUBLIC.STUDENT_CLASS ST_CLASS ON ST_CLASS.CLASS_ID = CLAS_S.ID
	INNER JOIN PUBLIC.STUDENT ST ON ST.ID = ST_CLASS.ST_ID where ST.emailid=?;
	
	C:\Program Files\PostgreSQL\17\pgAdmin 4\runtime\pg_dump.exe
	--file "C:\\Users\\User\\DOCUME~1\\STUDEN~1" --host "localhost"
	 --port "5432" --username "postgres" --no-password --format=p --inserts --create --verbose 
	 --table "public.attendance" 
	 --table "public.branch" 
	 --table "public.class" 
	 --table "public.course" 
	 --table "public.course_professor" 
	 --table "public.course_student"
	  --table "public.department"
	   --table "public.professor" 
	   --table "public.role" 
	   --table "public.student" 
	   --table "public.student_class"
	    --table "public.test"
	     --table "public.test_student" 
	     --table "public.user_data" 
	     --table "public.user_roles" 
	     --table "public.branch_id_seq" 
	     --table "public.class_id_seq" 
	     --table "public.course_id_seq" 
	     --table "public.department_id_seq" 
	     --table "public.professor_id_seq"
	      --table "public.role_id_seq" 
	      --table "public.student_id_seq" 
	      --table "public.test_id_seq" 
	      --table "public.user_data_id_seq" "student_management_system"
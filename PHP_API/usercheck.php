<?php
	require "dbCon.php";
	if(isset($_POST['username']))
	{
		$username= $_POST['username'];
		$query = "SELECT * FROM tb_user INNER JOIN student ON tb_user.i_student = student.student_id WHERE tb_user.username = '$username'";
		$data = mysqli_query($connect, $query);
		while ($row = mysqli_fetch_assoc($data)) 
        {
		$usercheck = new usercheck($row['username'], $row['student_phone']);
        }
		if(isset($data)&& isset($usercheck))
        {
			echo json_encode($usercheck);   
		}else{
				echo "Error";
		};
	}
?>
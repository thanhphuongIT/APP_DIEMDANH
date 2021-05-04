<?php
	require "dbCon.php";
	// $query ="Select * from tb_user where username = 'admin' and password ='123'";
	// $data = mysqli_query($connect, $query);
	// while($row = mysqli_fetch_assoc($data))
    // $test[] = $row;
	// echo json_encode($test);
	if(isset($_POST['username']) && isset($_POST['password']))
	{	
	$username= $_POST['username'];
	$password=$_POST['password'];
	$query = "Select * from tb_user where username = '$username' and password ='$password'";
	$data = mysqli_query($connect, $query);
	while ($row = mysqli_fetch_assoc($data)) {
	$user = new dbuser($row['id'], $row['username'], $row['password'], $row['oldpass'], $row['i_role'], $row['status']);}
	if(isset($data)&& isset($user)){
		echo json_encode($user);
	}else{
		echo "Error";
	};
	}
?>
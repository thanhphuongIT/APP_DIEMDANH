<?php
$connect = mysqli_connect("127.0.0.1","root","","student_attendence");
mysqli_query($connect, "SET NAMES 'utf8");
class dbuser{
		function __construct($id, $username, $password, $oldpass,$i_role,$status){
			$this->id = $id;
			$this->username  =$username;
			$this->password = $password;
			$this->oldpass = $oldpass;
			$this->i_role = $i_role;
			$this->status = $status;
		}
	}
?>
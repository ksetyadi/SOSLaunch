<?php

class Postit extends Controller
{
    function __construct()
    {
        parent::Controller();
    }
    
    function index()
    {
		// this is just an example
        $sqlString = "INSERT INTO [YOUR_TIMELINE_HERE]
                        SET phone_number = '" . $_POST['phone_number'] . "',
                            ipaddr = '" . $_POST['ipaddress'] . "',
                            latitude = '" . $_POST['latitude'] . "',
                            longitude = '" . $_POST['longitude'] . "',
                            message = '" . $_POST['message'] . "'";
                            
        $sqlQuery = mysql_query($sqlString);
		
		// post to Twitter
		// update the location (lat long)
		$username = base64_decode("PUT_YOUR_USERNAME_HERE_ENCODED_WITH_BASE64");
		$password = base64_decode("PUT_YOUR_USERNAME_HERE_ENCODED_WITH_BASE64");
		
		// preparing to send Direct Message to Twitter, if you want to
		$text = "d [YOUR_TWITTER_ACCOUNT_HERE] SOS LAUNCH:
				[PH: " . $_POST['phone_number'] . "]
				[IP: " . $_POST['ipaddress'] . "']
				[LT: " . $_POST['latitude'] . "']
				[LG: " . $_POST['longitude'] . "']
				[MSG: " . $_POST['message'] . "']";
											
		// I'm still using the basic authentication. You should change it using Twitter OAuth
		$url = "http://twitter.com/statuses/update.json?status=" . urlencode(stripslashes(urldecode($text)));
		
		$curl_handle = curl_init();
		curl_setopt($curl_handle, CURLOPT_URL, "$url");
		curl_setopt($curl_handle, CURLOPT_CONNECTTIMEOUT, 2);
		curl_setopt($curl_handle, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($curl_handle, CURLOPT_POST, 1);
		curl_setopt($curl_handle, CURLPOT_POSTFIELDS, "status=$text");
		curl_setopt($curl_handle, CURLOPT_USERPWD, "$username:$password");
		curl_setopt($curl_handle, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
		
		$buffer = curl_exec($curl_handle);
		curl_close($curl_handle);
		
		die();
    }
}

?>
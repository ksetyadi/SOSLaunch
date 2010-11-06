<?php

if (isset($_GET['ipreq']) && !empty($_GET['ipreq']))
{
    $contents = file_get_contents("http://whatismyipaddress.com/ip/" . $_GET['ipaddr']);
    
    $lat = preg_match("//", $contents);
    $long = preg_match("//", $contents);
    
    
    exit(0);
}

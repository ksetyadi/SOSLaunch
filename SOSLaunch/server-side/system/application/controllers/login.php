<?php

class Login extends Controller
{
    function __construct()
    {
        parent::Controller();
    }
    
    function index()
    {
        $this->load->view("login_form");
    }
}

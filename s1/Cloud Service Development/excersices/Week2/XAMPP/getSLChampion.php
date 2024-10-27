<?php
	$data = array();

	$nested_data = array();
	$nested_data['year'] = '2018-2019';
	$nested_data['image'] = 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Paok_logo.png/436px-Paok_logo.png';	    
	$data['paok'] = $nested_data;

	$nested_data = array();
	$nested_data['year'] = '2017-2018';
	$nested_data['image'] = 'https://upload.wikimedia.org/wikipedia/el/thumb/3/3b/AEK_Athens_FC_%28logo%29.svg/1200px-AEK_Athens_FC_%28logo%29.svg.png';	    
	$data['aek'] = $nested_data;

	$nested_data = array();
	$nested_data['year'] = '2019-2020';
	$nested_data['image'] = 'https://upload.wikimedia.org/wikipedia/el/b/b9/OSFP-logo.svg';	    
	$data['olympiakos'] = $nested_data;
		
	header("Content-Type: application/json");
	echo json_encode($data);
?>

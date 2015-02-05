
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Simple Contacts</title>
    <link href="${_r.contextPath}/assets/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${_r.contextPath}/css/sytel.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="sc" id="main">
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1 class="title">Simple Contacts</h1>
        </div>
    </div>
    <div id="GroupsList"></div>
    <div class="tab-content">
        <div class="tab-pane active" id="contacts">

            <div class="row">
                <div class="col-sm-12">
                    <div class="block">
                        <div id="ContactsList"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div id="modal-place"></div>
<script src="${_r.contextPath}/assets/vendors/jquery/jquery.min.js"></script>
<script src="${_r.contextPath}/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
<script src="${_r.contextPath}/assets/vendors/handlebars/handlebars-v2.0.0.js"></script>
<script src="${_r.contextPath}/assets/vendors/brite/brite.min.js"></script>
<script src="${_r.contextPath}/js/ContactDao.js"></script>
<script src="${_r.contextPath}/js/GroupDao.js"></script>
<script src="${_r.contextPath}/js/app.js"></script>
</body>
</html>


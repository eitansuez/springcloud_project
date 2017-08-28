<!DOCTYPE html>
<html>
<head>
  <title>Greetings</title>
  <link rel="stylesheet" href="/pui-v1.10.0/pivotal-ui.min.css">
  <link rel="stylesheet" href="/app.css">

  <script src="/pui-v1.10.0/pivotal-ui.min.js"></script>
</head>
<body>

<div class="aligner txt-c" style="padding-top:20%; padding-bottom:20%">
  <h1 class="aligner-item type-neutral-11 txt-c">${msg}</h1>

  <#if fortune?has_content>
    <h1 class="aligner-item type-neutral-11 txt-c">${fortune}</h1>
  </#if>

</div>
</body>
</html>
[#ftl]

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>

<!-- Custom JS -->
<script src="/js/custom.js" ></script>
<script src="/js/nicEdit.js" ></script>

<script type="text/javascript">

function toggleArea1() {
    var instructiuniTextArea;
	if(!instructiuniTextArea) {
		instructiuniTextArea = new nicEditor({fullPanel : true}).panelInstance('instructiuni',{hasPanel : true});
	} else {
		instructiuniTextArea.removeInstance('myArea1');
		instructiuniTextArea = null;
	}
}

bkLib.onDomLoaded(function() { toggleArea1(); });
</script>


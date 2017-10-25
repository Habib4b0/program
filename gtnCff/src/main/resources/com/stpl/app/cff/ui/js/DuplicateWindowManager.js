var openedWindows = [];
function addHyperLink() {
    var a = document.querySelectorAll('.opener a'), url, random, i, span = document.querySelectorAll('.opener a span');
    for (i = 0; i < a.length; i++) {
        (function (i) {
            a[i].onclick = function (e) {
                e.stopPropagation();
                e.preventDefault();
                var workflowId = "\""+span[i].innerHTML+"\"";
                if (!openedWindows[workflowId] || openedWindows[workflowId].closed) {
                    url = this.href;
                    console.log('New window opened ' + workflowId);
                    random = Math.floor((Math.random() * 100) + 1);
                    openedWindows[workflowId] = window.open(url, "_blank", random, "menubar = 0, scrollbars = 0");
                } else {
                    console.log('Window ' + workflowId + ' is already opened');
                }
                openedWindows[workflowId].focus();
            };
        })(i);
    }
};
/* Detect-zoom
 * -----------
 * Cross Browser Zoom and Pixel Ratio Detector
 * Version 1.0.4 | Apr 1 2013
 * dual-licensed under the WTFPL and MIT license
 * Maintained by https://github/tombigel
 * Original developer https://github.com/yonran
 */

//AMD and CommonJS initialization copied from https://github.com/zohararad/audio5js
(function (root, ns, factory) {
    "use strict";

    if (typeof (module) !== 'undefined' && module.exports) { // CommonJS
        module.exports = factory(ns, root);
    } else if (typeof (define) === 'function' && define.amd) { // AMD
        define("detect-zoom", function () {
            return factory(ns, root);
        });
    } else {
        root[ns] = factory(ns, root);
    }

}(window, 'detectZoom', function () {

    /**
     * Use devicePixelRatio if supported by the browser
     * @return {Number}
     * @private
     */
    var devicePixelRatio = function () {
        return window.devicePixelRatio || 1;
    };

    /**
     * Fallback function to set default values
     * @return {Object}
     * @private
     */
    var fallback = function () {
        return {
            zoom: 1,
            devicePxPerCssPx: 1
        };
    };
    /**
     * IE 8 and 9: no trick needed!
     * TODO: Test on IE10 and Windows 8 RT
     * @return {Object}
     * @private
     **/
    var ie8 = function () {
        var zoom = Math.round((screen.deviceXDPI / screen.logicalXDPI) * 100) / 100;
        return {
            zoom: zoom,
            devicePxPerCssPx: zoom * devicePixelRatio()
        };
    };

    /**
     * For IE10 we need to change our technique again...
     * thanks https://github.com/stefanvanburen
     * @return {Object}
     * @private
     */
    var ie10 = function () {
        var zoom = Math.round((document.documentElement.offsetHeight / window.innerHeight) * 100) / 100;
        return {
            zoom: zoom,
            devicePxPerCssPx: zoom * devicePixelRatio()
        };
    };

	/**
	* For chrome
	*
	*/
    var chrome = function()
    {
		var screenPixelRatio = 0, zoomLevel = 0;
	var div = document.createElement("div");
    div.style.display = 'flex';
    var s1 = div.style.display == 'flex';
    var s2 = 'perspective' in div.style;
    if(window.devicePixelRatio && s1 && s2)
        screenPixelRatio = window.devicePixelRatio;
    else if(window.screenX == '0')
        screenPixelRatio = (window.outerWidth - 8) / window.innerWidth;
    else
    {
        var scr = window.frames.screen;
        screenPixelRatio = scr.deviceXDPI / scr.systemXDPI;
    }

    
    if (screenPixelRatio <= .11){ //screenPixelRatio >= .01 &&
      zoomLevel = -6;
    } else if (screenPixelRatio <= .25) {
      zoomLevel = -5;
    }else if (screenPixelRatio <= .33) {
      zoomLevel = -4.5;
    } else if (screenPixelRatio <= .40) {
      zoomLevel = -4;
    } else if (screenPixelRatio <= .50) {
      zoomLevel = -3;
    } else if (screenPixelRatio <= .67) {
      zoomLevel = -2;
    } else if (screenPixelRatio <= .75) {
      zoomLevel = -1;
    } else if (screenPixelRatio <= .85) {
      zoomLevel = -0.5;
    } else if (screenPixelRatio <= .98) {
      zoomLevel = 0;
    } else if (screenPixelRatio <= 1.03) {
      zoomLevel = 1;
    } else if (screenPixelRatio <= 1.12) {
      zoomLevel = 1.5;
    } else if (screenPixelRatio <= 1.2) {
      zoomLevel = 2.5;
    } else if (screenPixelRatio <= 1.3) {
      zoomLevel = 3;
    } else if (screenPixelRatio <= 1.4) {
      zoomLevel = 3.5;
    } else if (screenPixelRatio <= 1.5) {
      zoomLevel = 4;
    } else if (screenPixelRatio <= 1.6) {
      zoomLevel = 4.3;
    } else if (screenPixelRatio <= 1.7) {
      zoomLevel = 4.7;
    } else if (screenPixelRatio <= 1.8) {
      zoomLevel = 5;
    } else if (screenPixelRatio <= 1.9) {
      zoomLevel = 5.5;
    } else if (screenPixelRatio <= 2) {
      zoomLevel = 6;
    } else if (screenPixelRatio <= 2.1) {
      zoomLevel = 6.2;
    } else if (screenPixelRatio <= 2.2) {
      zoomLevel = 6.4;
    } else if (screenPixelRatio <= 2.3) {
      zoomLevel = 6.6;
    } else if (screenPixelRatio <= 2.4) {
      zoomLevel = 6.8;
    } else if (screenPixelRatio <= 2.5) {
      zoomLevel = 7;
    } else if (screenPixelRatio <= 2.6) {
      zoomLevel = 7.2;
    } else if (screenPixelRatio <= 2.7) {
      zoomLevel = 7.4;
    } else if (screenPixelRatio <= 2.8) {
      zoomLevel = 7.6;
    } else if (screenPixelRatio <= 2.9) {
      zoomLevel = 7.8;
    } else if (screenPixelRatio <= 3) {
      zoomLevel = 8;
    } else if (screenPixelRatio <= 3.1) {
      zoomLevel = 8.1;
    } else if (screenPixelRatio <= 3.2) {
      zoomLevel = 8.2;
    } else if (screenPixelRatio <= 3.3) {
      zoomLevel = 8.3;
    } else if (screenPixelRatio <= 3.4) {
      zoomLevel = 8.4;
    } else if (screenPixelRatio <= 3.5) {
      zoomLevel = 8.5;
    } else if (screenPixelRatio <= 3.6) {
      zoomLevel = 8.6;
    } else if (screenPixelRatio <= 3.7) {
      zoomLevel = 8.7;
    } else if (screenPixelRatio <= 3.8) {
      zoomLevel = 8.8;
    } else if (screenPixelRatio <= 3.9) {
      zoomLevel = 8.9;
    } else if (screenPixelRatio <= 4) {
      zoomLevel = 9;
    } else if (screenPixelRatio <= 4.1) {
      zoomLevel = 9.1;
    } else if (screenPixelRatio <= 4.2) {
      zoomLevel = 9.2;
    } else if (screenPixelRatio <= 4.3) {
      zoomLevel = 9.3;
    } else if (screenPixelRatio <= 4.4) {
      zoomLevel = 9.4;
    } else if (screenPixelRatio <= 4.5) {
      zoomLevel = 9.5;
    } else if (screenPixelRatio <= 4.6) {
      zoomLevel = 9.6;
    } else if (screenPixelRatio <= 4.7) {
      zoomLevel = 9.7;
    } else if (screenPixelRatio <= 4.8) {
      zoomLevel = 9.8;
    } else if (screenPixelRatio <= 4.9) {
      zoomLevel = 9.9;
    } else if (screenPixelRatio <= 5) {
      zoomLevel = 10;
    }else {
      zoomLevel = -10;
    }
     return {
            zoom: zoomLevel,
            devicePxPerCssPx: zoomLevel * devicePixelRatio()
        };	    
    };

	/**
	* For safari (same as chrome)
	*
	*/
    var safari= function()
    {
    	var zoom = Math.round(((document.documentElement.clientWidth) / window.innerWidth)*100) / 100;
        return {
            zoom: zoom,
            devicePxPerCssPx: zoom * devicePixelRatio()
        };	    
    };
	

    /**
     * Mobile WebKit
     * the trick: window.innerWIdth is in CSS pixels, while
     * screen.width and screen.height are in system pixels.
     * And there are no scrollbars to mess up the measurement.
     * @return {Object}
     * @private
     */
    var webkitMobile = function () {
        var deviceWidth = (Math.abs(window.orientation) == 90) ? screen.height : screen.width;
        var zoom = deviceWidth / window.innerWidth;
        return {
            zoom: zoom,
            devicePxPerCssPx: zoom * devicePixelRatio()
        };
    };

    /**
     * Desktop Webkit
     * the trick: an element's clientHeight is in CSS pixels, while you can
     * set its line-height in system pixels using font-size and
     * -webkit-text-size-adjust:none.
     * device-pixel-ratio: http://www.webkit.org/blog/55/high-dpi-web-sites/
     *
     * Previous trick (used before http://trac.webkit.org/changeset/100847):
     * documentElement.scrollWidth is in CSS pixels, while
     * document.width was in system pixels. Note that this is the
     * layout width of the document, which is slightly different from viewport
     * because document width does not include scrollbars and might be wider
     * due to big elements.
     * @return {Object}
     * @private
     */
    var webkit = function () {
        var important = function (str) {
            return str.replace(/;/g, " !important;");
        };

        var div = document.createElement('div');
        div.innerHTML = "1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9<br>0";
        div.setAttribute('style', important('font: 100px/1em sans-serif; -webkit-text-size-adjust: none; text-size-adjust: none; height: auto; width: 1em; padding: 0; overflow: visible;'));

        // The container exists so that the div will be laid out in its own flow
        // while not impacting the layout, viewport size, or display of the
        // webpage as a whole.
        // Add !important and relevant CSS rule resets
        // so that other rules cannot affect the results.
        var container = document.createElement('div');
        container.setAttribute('style', important('width:0; height:0; overflow:hidden; visibility:hidden; position: absolute;'));
        container.appendChild(div);

        document.body.appendChild(container);
        var zoom = 1000 / div.clientHeight;
        zoom = Math.round(zoom * 100) / 100;
        document.body.removeChild(container);

        return{
            zoom: zoom,
            devicePxPerCssPx: zoom * devicePixelRatio()
        };
    };

    /**
     * no real trick; device-pixel-ratio is the ratio of device dpi / css dpi.
     * (Note that this is a different interpretation than Webkit's device
     * pixel ratio, which is the ratio device dpi / system dpi).
     *
     * Also, for Mozilla, there is no difference between the zoom factor and the device ratio.
     *
     * @return {Object}
     * @private
     */
    var firefox4 = function () {
        var zoom = mediaQueryBinarySearch('min--moz-device-pixel-ratio', '', 0, 10, 20, 0.0001);
        zoom = Math.round(zoom * 100) / 100;
        return {
            zoom: zoom,
            devicePxPerCssPx: zoom
        };
    };

    /**
     * Firefox 18.x
     * Mozilla added support for devicePixelRatio to Firefox 18,
     * but it is affected by the zoom level, so, like in older
     * Firefox we can't tell if we are in zoom mode or in a device
     * with a different pixel ratio
     * @return {Object}
     * @private
     */
    var firefox18 = function () {
        return {
            zoom: firefox4().zoom,
            devicePxPerCssPx: devicePixelRatio()
        };
    };

    /**
     * works starting Opera 11.11
     * the trick: outerWidth is the viewport width including scrollbars in
     * system px, while innerWidth is the viewport width including scrollbars
     * in CSS px
     * @return {Object}
     * @private
     */
    var opera11 = function () {
        var zoom = window.top.outerWidth / window.top.innerWidth;
        zoom = Math.round(zoom * 100) / 100;
        return {
            zoom: zoom,
            devicePxPerCssPx: zoom * devicePixelRatio()
        };
    };

    /**
     * Use a binary search through media queries to find zoom level in Firefox
     * @param property
     * @param unit
     * @param a
     * @param b
     * @param maxIter
     * @param epsilon
     * @return {Number}
     */
    var mediaQueryBinarySearch = function (property, unit, a, b, maxIter, epsilon) {
        var matchMedia;
        var head, style, div;
        if (window.matchMedia) {
            matchMedia = window.matchMedia;
        } else {
            head = document.getElementsByTagName('head')[0];
            style = document.createElement('style');
            head.appendChild(style);

            div = document.createElement('div');
            div.className = 'mediaQueryBinarySearch';
            div.style.display = 'none';
            document.body.appendChild(div);

            matchMedia = function (query) {
                style.sheet.insertRule('@media ' + query + '{.mediaQueryBinarySearch ' + '{text-decoration: underline} }', 0);
                var matched = getComputedStyle(div, null).textDecoration == 'underline';
                style.sheet.deleteRule(0);
                return {matches: matched};
            };
        }
        var ratio = binarySearch(a, b, maxIter);
        if (div) {
            head.removeChild(style);
            document.body.removeChild(div);
        }
        return ratio;

        function binarySearch(a, b, maxIter) {
            var mid = (a + b) / 2;
            if (maxIter <= 0 || b - a < epsilon) {
                return mid;
            }
            var query = "(" + property + ":" + mid + unit + ")";
            if (matchMedia(query).matches) {
                return binarySearch(mid, b, maxIter - 1);
            } else {
                return binarySearch(a, mid, maxIter - 1);
            }
        }
    };

    /**
     * Generate detection function
     * @private
     */
    var detectFunction = (function () {
        var func = fallback;
        //IE8+
        if (!isNaN(screen.logicalXDPI) && !isNaN(screen.systemXDPI)) {
            func = ie8;
        }
        // IE10+ / Touch
        else if (window.navigator.msMaxTouchPoints) {
            func = ie10;
        }
		//chrome
		else if(!!window.chrome && !(!!window.opera || navigator.userAgent.indexOf(' Opera') >= 0)){
			func = chrome;
		}
		//safari
		else if(Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0){
			func = safari;
		}	
        //Mobile Webkit
        else if ('orientation' in window && 'webkitRequestAnimationFrame' in window) {
            func = webkitMobile;
        }
        //WebKit
        else if ('webkitRequestAnimationFrame' in window) {
            func = webkit;
        }
        //Opera
        else if (navigator.userAgent.indexOf('Opera') >= 0) {
            func = opera11;
        }
        //Last one is Firefox
        //FF 18.x
        else if (window.devicePixelRatio) {
            func = firefox18;
        }
        //FF 4.0 - 17.x
        else if (firefox4().zoom > 0.001) {
            func = firefox4;
        }

        return func;
    }());
	/**
     * Generate detection function
     * @private
     */
    var detectFunctionName = (function () {
        var func = 'fallback';
        //IE8+
        if (!isNaN(screen.logicalXDPI) && !isNaN(screen.systemXDPI)) {
            func = 'ie8';
        }
        // IE10+ / Touch
        else if (window.navigator.msMaxTouchPoints) {
            func = 'ie10';
        }
		//chrome
		else if(!!window.chrome && !(!!window.opera || navigator.userAgent.indexOf(' Opera') >= 0)){
			func = 'chrome';
		}
		//safari
		else if(Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0){
			func = 'safari';
		}	
        //Mobile Webkit
        else if ('orientation' in window && 'webkitRequestAnimationFrame' in window) {
            func = 'webkitMobile';
        }
        //WebKit
        else if ('webkitRequestAnimationFrame' in window) {
            func = 'webkit';
        }
        //Opera
        else if (navigator.userAgent.indexOf('Opera') >= 0) {
            func = 'opera11';
        }
        //Last one is Firefox
        //FF 18.x
        else if (window.devicePixelRatio) {
            func = 'firefox18';
        }
        //FF 4.0 - 17.x
        else if (firefox4().zoom > 0.001) {
            func = 'firefox4';
        }

        return func;
    }());
	
    return ({

        /**
         * Ratios.zoom shorthand
         * @return {Number} Zoom level
         */
        zoom: function () {
            return detectFunction().zoom;
        },

        /**
         * Ratios.devicePxPerCssPx shorthand
         * @return {Number} devicePxPerCssPx level
         */
        device: function () {
            return detectFunction().devicePxPerCssPx;
        },

        /**
         * Ratios.devicePxPerCssPx shorthand
         * @return {Number} devicePxPerCssPx level
         */
        browserName: function () {
            return detectFunctionName;
        },

        /**
         * Ratios.devicePxPerCssPx shorthand
         * @return {Number} devicePxPerCssPx level
         */
        isResized: function () {
			var zoomlevel=detectFunction().zoom;
            return zoomlevel!==1;
        }
    });
}));    
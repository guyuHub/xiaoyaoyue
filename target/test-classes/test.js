/**
 * 
 */
var secretKey = "9cWbNgUqiL91raHPVmrP",
partnerCode = "123",
platform = "2",
repeatBones = 2,
version = "3.3.3",
flashPath = "",
dfpURL = "http://192.168.63.183:10010/api/device-fingerprint"; !
function() {
    function D(a) {
        var b, c, d, e = document.cookie.split(";");
        for (b = 0; b < e.length; b++) if (c = e[b].substr(0, e[b].indexOf("=")), d = e[b].substr(e[b].indexOf("=") + 1), c = c.replace(/^\s+|\s+$/g, ""), c == a) return unescape(d)
    }
    function E(a, b, c, d, e, f) {
        var h, g = new Date;
        g.setTime(g.getTime()),
        -1 != c ? (c = 24 * 60 * 60 * 1e3 * c, h = new Date(g.getTime() + c), cookieString = a + "=" + escape(b) + (c ? ";expires=" + h.toGMTString() : "") + (d ? ";path=" + d: "") + (e ? ";domain=" + e: "") + (f ? ";secure": "")) : (h = -1, cookieString = a + "=" + escape(b) + (c ? ";expires=" + h: "") + (d ? ";path=" + d: "") + (e ? ";domain=" + e: "") + (f ? ";secure": "")),
        document.cookie = cookieString
    }
    function G(a) {
        var b, c, d;
        if (a = a.replace(/\s/g, ""), b = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/, b.test(a)) {
            if (c = a.split("."), 0 == parseInt(parseFloat(c[0]))) return ! 1;
            if (0 == parseInt(parseFloat(c[3]))) return ! 1;
            for (d = 0; d < c.length; d++) if (parseInt(parseFloat(c[d])) > 255) return ! 1;
            return ! 0
        }
        return ! 1
    }
    function I() {
        var c, d, a = "",
        b = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
        for (c = 0; 16 > c; c++) d = Math.ceil(35 * Math.random()),
        a += b[d];
        return a
    }
    function K(a, b) {
        this.key = a,
        this.value = b
    }
    function E(a, b, d) {
        var e = new Date;
        e.setTime(e.getTime() + 1e3 * 3600 * 24 * Number(d)),
        document.cookie = a + "=" + b + "; path=/;expires = " + e.toGMTString() + ";domain=" + c(window.location.host.split(":")[0])
    }
    function R() {
        this.ec = new evercookie,
        this.deviceEc = new evercookie,
        this.cfp = new Fingerprint2,
        this.moreInfoArray = []
    }
    function S() {
        var b, a = D("BSFIT_EXPIRATION");
        if ( - 1 == a) for (b = 0; 10 > b; b++) setTimeout(function() { (new R).getFingerPrint()
        },
        20 + 100 * 20 * Math.pow(b, repeatBones));
        else setTimeout(function() { (new R).getFingerPrint()
        },
        a - (new Date).getTime())
    }
    var a, c, B, M, Q; !
    function(a, b, c) {
        "use strict";
        "undefined" != typeof module && module.exports ? module.exports = c() : "function" == typeof define && define.amd ? define(c) : b[a] = c()
    } ("Fingerprint2", this,
    function() {
        "use strict";
        Array.prototype.indexOf || (Array.prototype.indexOf = function(a, b) {
            var c, d, e, f;
            if (null == this) throw new TypeError("'this' is null or undefined");
            if (d = Object(this), e = d.length >>> 0, 0 === e) return - 1;
            if (f = +b || 0, 1 / 0 === Math.abs(f) && (f = 0), f >= e) return - 1;
            for (c = Math.max(f >= 0 ? f: e - Math.abs(f), 0); e > c;) {
                if (c in d && d[c] === a) return c;
                c++
            }
            return - 1
        });
        var a = function(a) {
            var b = {
                swfContainerId: "fingerprintjs2",
                swfPath: "flash/compiled/FontList.swf",
                detectScreenOrientation: !0,
                sortPluginsFor: [/palemoon/i]
            };
            this.options = this.extend(a, b),
            this.nativeForEach = Array.prototype.forEach,
            this.nativeMap = Array.prototype.map
        };
        return a.prototype = {
            extend: function(a, b) {
                if (null == a) return b;
                for (var c in a) null != a[c] && b[c] !== a[c] && (b[c] = a[c]);
                return b
            },
            log: function(a) {
                window.console && console.log(a)
            },
            get: function(a) {
                var c, b = [];
                b = this.userAgentKey(b),
                b = this.languageKey(b),
                b = this.colorDepthKey(b),
                b = this.screenResolutionKey(b),
                b = this.timezoneOffsetKey(b),
                b = this.sessionStorageKey(b),
                b = this.localStorageKey(b),
                b = this.indexedDbKey(b),
                b = this.addBehaviorKey(b),
                b = this.openDatabaseKey(b),
                b = this.cpuClassKey(b),
                b = this.platformKey(b),
                b = this.doNotTrackKey(b),
                b = this.pluginsKey(b),
                b = this.canvasKey(b),
                b = this.webglKey(b),
                b = this.adBlockKey(b),
                b = this.hasLiedLanguagesKey(b),
                b = this.hasLiedResolutionKey(b),
                b = this.hasLiedOsKey(b),
                b = this.hasLiedBrowserKey(b),
                b = this.touchSupportKey(b),
                c = this,
                this.fontsKey(b,
                function(b) {
                    var e, d = [];
                    return c.each(b,
                    function(a) {
                        var b = a.value;
                        "undefined" != typeof a.value.join && (b = a.value.join(";")),
                        d.push(b)
                    }),
                    e = c.x64hash128(d.join("~~~"), 31),
                    a(e, b)
                })
            },
            userAgentKey: function(a) {
                return this.options.excludeUserAgent || a.push({
                    key: "user_agent",
                    value: this.getUserAgent()
                }),
                a
            },
            getUserAgent: function() {
                return navigator.userAgent
            },
            languageKey: function(a) {
                return this.options.excludeLanguage || a.push({
                    key: "language",
                    value: navigator.language || navigator.userLanguage || navigator.browserLanguage || navigator.systemLanguage
                }),
                a
            },
            colorDepthKey: function(a) {
                return this.options.excludeColorDepth || a.push({
                    key: "color_depth",
                    value: screen.colorDepth
                }),
                a
            },
            screenResolutionKey: function(a) {
                return this.options.excludeScreenResolution ? a: this.getScreenResolution(a)
            },
            getScreenResolution: function(a) {
                var b, c;
                return b = this.options.detectScreenOrientation ? screen.height > screen.width ? [screen.height, screen.width] : [screen.width, screen.height] : [screen.width, screen.height],
                "undefined" != typeof b && a.push({
                    key: "resolution",
                    value: b
                }),
                screen.availWidth && screen.availHeight && (c = this.options.detectScreenOrientation ? screen.availHeight > screen.availWidth ? [screen.availHeight, screen.availWidth] : [screen.availWidth, screen.availHeight] : [screen.availHeight, screen.availWidth]),
                "undefined" != typeof c && a.push({
                    key: "available_resolution",
                    value: c
                }),
                a
            },
            timezoneOffsetKey: function(a) {
                return this.options.excludeTimezoneOffset || a.push({
                    key: "timezone_offset",
                    value: (new Date).getTimezoneOffset()
                }),
                a
            },
            sessionStorageKey: function(a) {
                return ! this.options.excludeSessionStorage && this.hasSessionStorage() && a.push({
                    key: "session_storage",
                    value: 1
                }),
                a
            },
            localStorageKey: function(a) {
                return ! this.options.excludeSessionStorage && this.hasLocalStorage() && a.push({
                    key: "local_storage",
                    value: 1
                }),
                a
            },
            indexedDbKey: function(a) {
                return ! this.options.excludeIndexedDB && this.hasIndexedDB() && a.push({
                    key: "indexed_db",
                    value: 1
                }),
                a
            },
            addBehaviorKey: function(a) {
                return document.body && !this.options.excludeAddBehavior && document.body.addBehavior && a.push({
                    key: "add_behavior",
                    value: 1
                }),
                a
            },
            openDatabaseKey: function(a) {
                return ! this.options.excludeOpenDatabase && window.openDatabase && a.push({
                    key: "open_database",
                    value: 1
                }),
                a
            },
            cpuClassKey: function(a) {
                return this.options.excludeCpuClass || a.push({
                    key: "cpu_class",
                    value: this.getNavigatorCpuClass()
                }),
                a
            },
            platformKey: function(a) {
                return this.options.excludePlatform || a.push({
                    key: "navigator_platform",
                    value: this.getNavigatorPlatform()
                }),
                a
            },
            doNotTrackKey: function(a) {
                return this.options.excludeDoNotTrack || a.push({
                    key: "do_not_track",
                    value: this.getDoNotTrack()
                }),
                a
            },
            canvasKey: function(a) {
                return ! this.options.excludeCanvas && this.isCanvasSupported() && a.push({
                    key: "canvas",
                    value: this.getCanvasFp()
                }),
                a
            },
            webglKey: function(a) {
                return this.options.excludeWebGL ? ("undefined" == typeof NODEBUG && this.log("Skipping WebGL fingerprinting per excludeWebGL configuration option"), a) : this.isWebGlSupported() ? (a.push({
                    key: "webgl",
                    value: this.getWebglFp()
                }), a) : ("undefined" == typeof NODEBUG && this.log("Skipping WebGL fingerprinting because it is not supported in this browser"), a)
            },
            adBlockKey: function(a) {
                return this.options.excludeAdBlock || a.push({
                    key: "adblock",
                    value: this.getAdBlock()
                }),
                a
            },
            hasLiedLanguagesKey: function(a) {
                return this.options.excludeHasLiedLanguages || a.push({
                    key: "has_lied_languages",
                    value: this.getHasLiedLanguages()
                }),
                a
            },
            hasLiedResolutionKey: function(a) {
                return this.options.excludeHasLiedResolution || a.push({
                    key: "has_lied_resolution",
                    value: this.getHasLiedResolution()
                }),
                a
            },
            hasLiedOsKey: function(a) {
                return this.options.excludeHasLiedOs || a.push({
                    key: "has_lied_os",
                    value: this.getHasLiedOs()
                }),
                a
            },
            hasLiedBrowserKey: function(a) {
                return this.options.excludeHasLiedBrowser || a.push({
                    key: "has_lied_browser",
                    value: this.getHasLiedBrowser()
                }),
                a
            },
            fontsKey: function(a, b) {
                return this.options.excludeJsFonts ? this.flashFontsKey(a, b) : this.jsFontsKey(a, b)
            },
            flashFontsKey: function(a, b) {
                return this.options.excludeFlashFonts ? ("undefined" == typeof NODEBUG && this.log("Skipping flash fonts detection per excludeFlashFonts configuration option"), b(a)) : this.hasSwfObjectLoaded() ? this.hasMinFlashInstalled() ? "undefined" == typeof this.options.swfPath ? ("undefined" == typeof NODEBUG && this.log("To use Flash fonts detection, you must pass a valid swfPath option, skipping Flash fonts enumeration"), b(a)) : (this.loadSwfAndDetectFonts(function(c) {
                    a.push({
                        key: "swf_fonts",
                        value: c.join(";")
                    }),
                    b(a)
                }), void 0) : ("undefined" == typeof NODEBUG && this.log("Flash is not installed, skipping Flash fonts enumeration"), b(a)) : ("undefined" == typeof NODEBUG && this.log("Swfobject is not detected, Flash fonts enumeration is skipped"), b(a))
            },
            jsFontsKey: function(a, b) {
                var c = this;
                return setTimeout(function() {
                    var i, j, k, l, m, n, o, p, q, d = ["monospace", "sans-serif", "serif"],
                    e = "mmmmmmmmmmlli",
                    f = "72px",
                    g = document.getElementsByTagName("body")[0],
                    h = document.createElement("span");
                    h.style.fontSize = f,
                    h.innerHTML = e,
                    i = {},
                    j = {};
                    for (k in d) h.style.fontFamily = d[k],
                    g.appendChild(h),
                    i[d[k]] = h.offsetWidth,
                    j[d[k]] = h.offsetHeight,
                    g.removeChild(h);
                    for (l = function(a) {
                        var c, e, b = !1;
                        for (c in d) h.style.fontFamily = a + "," + d[c],
                        g.appendChild(h),
                        e = h.offsetWidth !== i[d[c]] || h.offsetHeight !== j[d[c]],
                        g.removeChild(h),
                        b = b || e;
                        return b
                    },
                    m = ["Andale Mono", "Arial", "Arial Black", "Arial Hebrew", "Arial MT", "Arial Narrow", "Arial Rounded MT Bold", "Arial Unicode MS", "Bitstream Vera Sans Mono", "Book Antiqua", "Bookman Old Style", "Calibri", "Cambria", "Cambria Math", "Century", "Century Gothic", "Century Schoolbook", "Comic Sans", "Comic Sans MS", "Consolas", "Courier", "Courier New", "Garamond", "Geneva", "Georgia", "Helvetica", "Helvetica Neue", "Impact", "Lucida Bright", "Lucida Calligraphy", "Lucida Console", "Lucida Fax", "LUCIDA GRANDE", "Lucida Handwriting", "Lucida Sans", "Lucida Sans Typewriter", "Lucida Sans Unicode", "Microsoft Sans Serif", "Monaco", "Monotype Corsiva", "MS Gothic", "MS Outlook", "MS PGothic", "MS Reference Sans Serif", "MS Sans Serif", "MS Serif", "MYRIAD", "MYRIAD PRO", "Palatino", "Palatino Linotype", "Segoe Print", "Segoe Script", "Segoe UI", "Segoe UI Light", "Segoe UI Semibold", "Segoe UI Symbol", "Tahoma", "Times", "Times New Roman", "Times New Roman PS", "Trebuchet MS", "Verdana", "Wingdings", "Wingdings 2", "Wingdings 3"], n = ["Abadi MT Condensed Light", "Academy Engraved LET", "ADOBE CASLON PRO", "Adobe Garamond", "ADOBE GARAMOND PRO", "Agency FB", "Aharoni", "Albertus Extra Bold", "Albertus Medium", "Algerian", "Amazone BT", "American Typewriter", "American Typewriter Condensed", "AmerType Md BT", "Andalus", "Angsana New", "AngsanaUPC", "Antique Olive", "Aparajita", "Apple Chancery", "Apple Color Emoji", "Apple SD Gothic Neo", "Arabic Typesetting", "ARCHER", "ARNO PRO", "Arrus BT", "Aurora Cn BT", "AvantGarde Bk BT", "AvantGarde Md BT", "AVENIR", "Ayuthaya", "Bandy", "Bangla Sangam MN", "Bank Gothic", "BankGothic Md BT", "Baskerville", "Baskerville Old Face", "Batang", "BatangChe", "Bauer Bodoni", "Bauhaus 93", "Bazooka", "Bell MT", "Bembo", "Benguiat Bk BT", "Berlin Sans FB", "Berlin Sans FB Demi", "Bernard MT Condensed", "BernhardFashion BT", "BernhardMod BT", "Big Caslon", "BinnerD", "Blackadder ITC", "BlairMdITC TT", "Bodoni 72", "Bodoni 72 Oldstyle", "Bodoni 72 Smallcaps", "Bodoni MT", "Bodoni MT Black", "Bodoni MT Condensed", "Bodoni MT Poster Compressed", "Bookshelf Symbol 7", "Boulder", "Bradley Hand", "Bradley Hand ITC", "Bremen Bd BT", "Britannic Bold", "Broadway", "Browallia New", "BrowalliaUPC", "Brush Script MT", "Californian FB", "Calisto MT", "Calligrapher", "Candara", "CaslonOpnface BT", "Castellar", "Centaur", "Cezanne", "CG Omega", "CG Times", "Chalkboard", "Chalkboard SE", "Chalkduster", "Charlesworth", "Charter Bd BT", "Charter BT", "Chaucer", "ChelthmITC Bk BT", "Chiller", "Clarendon", "Clarendon Condensed", "CloisterBlack BT", "Cochin", "Colonna MT", "Constantia", "Cooper Black", "Copperplate", "Copperplate Gothic", "Copperplate Gothic Bold", "Copperplate Gothic Light", "CopperplGoth Bd BT", "Corbel", "Cordia New", "CordiaUPC", "Cornerstone", "Coronet", "Cuckoo", "Curlz MT", "DaunPenh", "Dauphin", "David", "DB LCD Temp", "DELICIOUS", "Denmark", "DFKai-SB", "Didot", "DilleniaUPC", "DIN", "DokChampa", "Dotum", "DotumChe", "Ebrima", "Edwardian Script ITC", "Elephant", "English 111 Vivace BT", "Engravers MT", "EngraversGothic BT", "Eras Bold ITC", "Eras Demi ITC", "Eras Light ITC", "Eras Medium ITC", "EucrosiaUPC", "Euphemia", "Euphemia UCAS", "EUROSTILE", "Exotc350 Bd BT", "FangSong", "Felix Titling", "Fixedsys", "FONTIN", "Footlight MT Light", "Forte", "FrankRuehl", "Fransiscan", "Freefrm721 Blk BT", "FreesiaUPC", "Freestyle Script", "French Script MT", "FrnkGothITC Bk BT", "Fruitger", "FRUTIGER", "Futura", "Futura Bk BT", "Futura Lt BT", "Futura Md BT", "Futura ZBlk BT", "FuturaBlack BT", "Gabriola", "Galliard BT", "Gautami", "Geeza Pro", "Geometr231 BT", "Geometr231 Hv BT", "Geometr231 Lt BT", "GeoSlab 703 Lt BT", "GeoSlab 703 XBd BT", "Gigi", "Gill Sans", "Gill Sans MT", "Gill Sans MT Condensed", "Gill Sans MT Ext Condensed Bold", "Gill Sans Ultra Bold", "Gill Sans Ultra Bold Condensed", "Gisha", "Gloucester MT Extra Condensed", "GOTHAM", "GOTHAM BOLD", "Goudy Old Style", "Goudy Stout", "GoudyHandtooled BT", "GoudyOLSt BT", "Gujarati Sangam MN", "Gulim", "GulimChe", "Gungsuh", "GungsuhChe", "Gurmukhi MN", "Haettenschweiler", "Harlow Solid Italic", "Harrington", "Heather", "Heiti SC", "Heiti TC", "HELV", "Herald", "High Tower Text", "Hiragino Kaku Gothic ProN", "Hiragino Mincho ProN", "Hoefler Text", "Humanst 521 Cn BT", "Humanst521 BT", "Humanst521 Lt BT", "Imprint MT Shadow", "Incised901 Bd BT", "Incised901 BT", "Incised901 Lt BT", "INCONSOLATA", "Informal Roman", "Informal011 BT", "INTERSTATE", "IrisUPC", "Iskoola Pota", "JasmineUPC", "Jazz LET", "Jenson", "Jester", "Jokerman", "Juice ITC", "Kabel Bk BT", "Kabel Ult BT", "Kailasa", "KaiTi", "Kalinga", "Kannada Sangam MN", "Kartika", "Kaufmann Bd BT", "Kaufmann BT", "Khmer UI", "KodchiangUPC", "Kokila", "Korinna BT", "Kristen ITC", "Krungthep", "Kunstler Script", "Lao UI", "Latha", "Leelawadee", "Letter Gothic", "Levenim MT", "LilyUPC", "Lithograph", "Lithograph Light", "Long Island", "Lydian BT", "Magneto", "Maiandra GD", "Malayalam Sangam MN", "Malgun Gothic", "Mangal", "Marigold", "Marion", "Marker Felt", "Market", "Marlett", "Matisse ITC", "Matura MT Script Capitals", "Meiryo", "Meiryo UI", "Microsoft Himalaya", "Microsoft JhengHei", "Microsoft New Tai Lue", "Microsoft PhagsPa", "Microsoft Tai Le", "Microsoft Uighur", "Microsoft YaHei", "Microsoft Yi Baiti", "MingLiU", "MingLiU_HKSCS", "MingLiU_HKSCS-ExtB", "MingLiU-ExtB", "Minion", "Minion Pro", "Miriam", "Miriam Fixed", "Mistral", "Modern", "Modern No. 20", "Mona Lisa Solid ITC TT", "Mongolian Baiti", "MONO", "MoolBoran", "Mrs Eaves", "MS LineDraw", "MS Mincho", "MS PMincho", "MS Reference Specialty", "MS UI Gothic", "MT Extra", "MUSEO", "MV Boli", "Nadeem", "Narkisim", "NEVIS", "News Gothic", "News GothicMT", "NewsGoth BT", "Niagara Engraved", "Niagara Solid", "Noteworthy", "NSimSun", "Nyala", "OCR A Extended", "Old Century", "Old English Text MT", "Onyx", "Onyx BT", "OPTIMA", "Oriya Sangam MN", "OSAKA", "OzHandicraft BT", "Palace Script MT", "Papyrus", "Parchment", "Party LET", "Pegasus", "Perpetua", "Perpetua Titling MT", "PetitaBold", "Pickwick", "Plantagenet Cherokee", "Playbill", "PMingLiU", "PMingLiU-ExtB", "Poor Richard", "Poster", "PosterBodoni BT", "PRINCETOWN LET", "Pristina", "PTBarnum BT", "Pythagoras", "Raavi", "Rage Italic", "Ravie", "Ribbon131 Bd BT", "Rockwell", "Rockwell Condensed", "Rockwell Extra Bold", "Rod", "Roman", "Sakkal Majalla", "Santa Fe LET", "Savoye LET", "Sceptre", "Script", "Script MT Bold", "SCRIPTINA", "Serifa", "Serifa BT", "Serifa Th BT", "ShelleyVolante BT", "Sherwood", "Shonar Bangla", "Showcard Gothic", "Shruti", "Signboard", "SILKSCREEN", "SimHei", "Simplified Arabic", "Simplified Arabic Fixed", "SimSun", "SimSun-ExtB", "Sinhala Sangam MN", "Sketch Rockwell", "Skia", "Small Fonts", "Snap ITC", "Snell Roundhand", "Socket", "Souvenir Lt BT", "Staccato222 BT", "Steamer", "Stencil", "Storybook", "Styllo", "Subway", "Swis721 BlkEx BT", "Swiss911 XCm BT", "Sylfaen", "Synchro LET", "System", "Tamil Sangam MN", "Technical", "Teletype", "Telugu Sangam MN", "Tempus Sans ITC", "Terminal", "Thonburi", "Traditional Arabic", "Trajan", "TRAJAN PRO", "Tristan", "Tubular", "Tunga", "Tw Cen MT", "Tw Cen MT Condensed", "Tw Cen MT Condensed Extra Bold", "TypoUpright BT", "Unicorn", "Univers", "Univers CE 55 Medium", "Univers Condensed", "Utsaah", "Vagabond", "Vani", "Vijaya", "Viner Hand ITC", "VisualUI", "Vivaldi", "Vladimir Script", "Vrinda", "Westminster", "WHITNEY", "Wide Latin", "ZapfEllipt BT", "ZapfHumnst BT", "ZapfHumnst Dm BT", "Zapfino", "Zurich BlkEx BT", "Zurich Ex BT", "ZWAdobeF"], c.options.extendedJsFonts && (m = m.concat(n)), o = [], p = 0, q = m.length; q > p; p++) l(m[p]) && o.push(m[p]);
                    a.push({
                        key: "js_fonts",
                        value: o
                    }),
                    b(a)
                },
                1)
            },
            pluginsKey: function(a) {
                return this.options.excludePlugins || (this.isIE() ? a.push({
                    key: "ie_plugins",
                    value: this.getIEPlugins()
                }) : a.push({
                    key: "regular_plugins",
                    value: this.getRegularPlugins()
                })),
                a
            },
            getRegularPlugins: function() {
                var b, c, a = [];
                for (b = 0, c = navigator.plugins.length; c > b; b++) a.push(navigator.plugins[b]);
                return this.pluginsShouldBeSorted() && (a = a.sort(function(a, b) {
                    return a.name > b.name ? 1 : a.name < b.name ? -1 : 0
                })),
                this.map(a,
                function(a) {
                    var b = this.map(a,
                    function(a) {
                        return [a.type, a.suffixes].join("~")
                    }).join(",");
                    return [a.name, a.description, b].join("::")
                },
                this)
            },
            getIEPlugins: function() {
                if (window.ActiveXObject) {
                    var a = ["AcroPDF.PDF", "Adodb.Stream", "AgControl.AgControl", "DevalVRXCtrl.DevalVRXCtrl.1", "MacromediaFlashPaper.MacromediaFlashPaper", "Msxml2.DOMDocument", "Msxml2.XMLHTTP", "PDF.PdfCtrl", "QuickTime.QuickTime", "QuickTimeCheckObject.QuickTimeCheck.1", "RealPlayer", "RealPlayer.RealPlayer(tm) ActiveX Control (32-bit)", "RealVideo.RealVideo(tm) ActiveX Control (32-bit)", "Scripting.Dictionary", "SWCtl.SWCtl", "Shell.UIHelper", "ShockwaveFlash.ShockwaveFlash", "Skype.Detection", "TDCCtl.TDCCtl", "WMPlayer.OCX", "rmocx.RealPlayer G2 Control", "rmocx.RealPlayer G2 Control.1"];
                    return this.map(a,
                    function(a) {
                        try {
                            return new ActiveXObject(a),
                            a
                        } catch(b) {
                            return null
                        }
                    })
                }
                return []
            },
            pluginsShouldBeSorted: function() {
                var b, c, d, a = !1;
                for (b = 0, c = this.options.sortPluginsFor.length; c > b; b++) if (d = this.options.sortPluginsFor[b], navigator.userAgent.match(d)) {
                    a = !0;
                    break
                }
                return a
            },
            touchSupportKey: function(a) {
                return this.options.excludeTouchSupport || a.push({
                    key: "touch_support",
                    value: this.getTouchSupport()
                }),
                a
            },
            hasSessionStorage: function() {
                try {
                    return !! window.sessionStorage
                } catch(a) {
                    return ! 0
                }
            },
            hasLocalStorage: function() {
                try {
                    return !! window.localStorage
                } catch(a) {
                    return ! 0
                }
            },
            hasIndexedDB: function() {
                return !! window.indexedDB
            },
            getNavigatorCpuClass: function() {
                return navigator.cpuClass ? navigator.cpuClass: "unknown"
            },
            getNavigatorPlatform: function() {
                return navigator.platform ? navigator.platform: "unknown"
            },
            getDoNotTrack: function() {
                return navigator.doNotTrack ? navigator.doNotTrack: "unknown"
            },
            getTouchSupport: function() {
                var d, a = 0,
                b = !1;
                "undefined" != typeof navigator.maxTouchPoints ? a = navigator.maxTouchPoints: "undefined" != typeof navigator.msMaxTouchPoints && (a = navigator.msMaxTouchPoints);
                try {
                    document.createEvent("TouchEvent"),
                    b = !0
                } catch(c) {}
                return d = "ontouchstart" in window,
                [a, b, d]
            },
            getCanvasFp: function() {
                var c, a = [],
                b = document.createElement("canvas");
                return b.width = 2e3,
                b.height = 200,
                b.style.display = "inline",
                c = b.getContext("2d"),
                c.rect(0, 0, 10, 10),
                c.rect(2, 2, 6, 6),
                a.push("canvas winding:" + (c.isPointInPath(5, 5, "evenodd") === !1 ? "yes": "no")),
                c.textBaseline = "alphabetic",
                c.fillStyle = "#f60",
                c.fillRect(125, 1, 62, 20),
                c.fillStyle = "#069",
                c.font = this.options.dontUseFakeFontInCanvas ? "11pt Arial": "11pt no-real-font-123",
                c.fillText("Cwm fjordbank glyphs vext quiz, ??", 2, 15),
                c.fillStyle = "rgba(102, 204, 0, 0.7)",
                c.font = "18pt Arial",
                c.fillText("Cwm fjordbank glyphs vext quiz, ??", 4, 45),
                c.globalCompositeOperation = "multiply",
                c.fillStyle = "rgb(255,0,255)",
                c.beginPath(),
                c.arc(50, 50, 50, 0, 2 * Math.PI, !0),
                c.closePath(),
                c.fill(),
                c.fillStyle = "rgb(0,255,255)",
                c.beginPath(),
                c.arc(100, 50, 50, 0, 2 * Math.PI, !0),
                c.closePath(),
                c.fill(),
                c.fillStyle = "rgb(255,255,0)",
                c.beginPath(),
                c.arc(75, 100, 50, 0, 2 * Math.PI, !0),
                c.closePath(),
                c.fill(),
                c.fillStyle = "rgb(255,0,255)",
                c.arc(75, 75, 75, 0, 2 * Math.PI, !0),
                c.arc(75, 75, 25, 0, 2 * Math.PI, !0),
                c.fill("evenodd"),
                a.push("canvas fp:" + b.toDataURL()),
                a.join("~")
            },
            getWebglFp: function() {
                var d, e, f, g, h, i, j, k, b = function(b) {
                    return a.clearColor(0, 0, 0, 1),
                    a.enable(a.DEPTH_TEST),
                    a.depthFunc(a.LEQUAL),
                    a.clear(a.COLOR_BUFFER_BIT | a.DEPTH_BUFFER_BIT),
                    "[" + b[0] + ", " + b[1] + "]"
                },
                c = function(a) {
                    var b, c = a.getExtension("EXT_texture_filter_anisotropic") || a.getExtension("WEBKIT_EXT_texture_filter_anisotropic") || a.getExtension("MOZ_EXT_texture_filter_anisotropic");
                    return c ? (b = a.getParameter(c.MAX_TEXTURE_MAX_ANISOTROPY_EXT), 0 === b && (b = 2), b) : null
                },
                a = this.getWebglCanvas();
                return a ? (d = [], e = "attribute vec2 attrVertex;varying vec2 varyinTexCoordinate;uniform vec2 uniformOffset;void main(){varyinTexCoordinate=attrVertex+uniformOffset;gl_Position=vec4(attrVertex,0,1);}", f = "precision mediump float;varying vec2 varyinTexCoordinate;void main() {gl_FragColor=vec4(varyinTexCoordinate,0,1);}", g = a.createBuffer(), a.bindBuffer(a.ARRAY_BUFFER, g), h = new Float32Array([ - .2, -.9, 0, .4, -.26, 0, 0, .732134444, 0]), a.bufferData(a.ARRAY_BUFFER, h, a.STATIC_DRAW), g.itemSize = 3, g.numItems = 3, i = a.createProgram(), j = a.createShader(a.VERTEX_SHADER), a.shaderSource(j, e), a.compileShader(j), k = a.createShader(a.FRAGMENT_SHADER), a.shaderSource(k, f), a.compileShader(k), a.attachShader(i, j), a.attachShader(i, k), a.linkProgram(i), a.useProgram(i), i.vertexPosAttrib = a.getAttribLocation(i, "attrVertex"), i.offsetUniform = a.getUniformLocation(i, "uniformOffset"), a.enableVertexAttribArray(i.vertexPosArray), a.vertexAttribPointer(i.vertexPosAttrib, g.itemSize, a.FLOAT, !1, 0, 0), a.uniform2f(i.offsetUniform, 1, 1), a.drawArrays(a.TRIANGLE_STRIP, 0, g.numItems), null != a.canvas && d.push(a.canvas.toDataURL()), d.push("extensions:" + a.getSupportedExtensions().join(";")), d.push("webgl aliased line width range:" + b(a.getParameter(a.ALIASED_LINE_WIDTH_RANGE))), d.push("webgl aliased point size range:" + b(a.getParameter(a.ALIASED_POINT_SIZE_RANGE))), d.push("webgl alpha bits:" + a.getParameter(a.ALPHA_BITS)), d.push("webgl antialiasing:" + (a.getContextAttributes().antialias ? "yes": "no")), d.push("webgl blue bits:" + a.getParameter(a.BLUE_BITS)), d.push("webgl depth bits:" + a.getParameter(a.DEPTH_BITS)), d.push("webgl green bits:" + a.getParameter(a.GREEN_BITS)), d.push("webgl max anisotropy:" + c(a)), d.push("webgl max combined texture image units:" + a.getParameter(a.MAX_COMBINED_TEXTURE_IMAGE_UNITS)), d.push("webgl max cube map texture size:" + a.getParameter(a.MAX_CUBE_MAP_TEXTURE_SIZE)), d.push("webgl max fragment uniform vectors:" + a.getParameter(a.MAX_FRAGMENT_UNIFORM_VECTORS)), d.push("webgl max render buffer size:" + a.getParameter(a.MAX_RENDERBUFFER_SIZE)), d.push("webgl max texture image units:" + a.getParameter(a.MAX_TEXTURE_IMAGE_UNITS)), d.push("webgl max texture size:" + a.getParameter(a.MAX_TEXTURE_SIZE)), d.push("webgl max varying vectors:" + a.getParameter(a.MAX_VARYING_VECTORS)), d.push("webgl max vertex attribs:" + a.getParameter(a.MAX_VERTEX_ATTRIBS)), d.push("webgl max vertex texture image units:" + a.getParameter(a.MAX_VERTEX_TEXTURE_IMAGE_UNITS)), d.push("webgl max vertex uniform vectors:" + a.getParameter(a.MAX_VERTEX_UNIFORM_VECTORS)), d.push("webgl max viewport dims:" + b(a.getParameter(a.MAX_VIEWPORT_DIMS))), d.push("webgl red bits:" + a.getParameter(a.RED_BITS)), d.push("webgl renderer:" + a.getParameter(a.RENDERER)), d.push("webgl shading language version:" + a.getParameter(a.SHADING_LANGUAGE_VERSION)), d.push("webgl stencil bits:" + a.getParameter(a.STENCIL_BITS)), d.push("webgl vendor:" + a.getParameter(a.VENDOR)), d.push("webgl version:" + a.getParameter(a.VERSION)), a.getShaderPrecisionFormat ? (d.push("webgl vertex shader high float precision:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.HIGH_FLOAT).precision), d.push("webgl vertex shader high float precision rangeMin:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.HIGH_FLOAT).rangeMin), d.push("webgl vertex shader high float precision rangeMax:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.HIGH_FLOAT).rangeMax), d.push("webgl vertex shader medium float precision:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.MEDIUM_FLOAT).precision), d.push("webgl vertex shader medium float precision rangeMin:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.MEDIUM_FLOAT).rangeMin), d.push("webgl vertex shader medium float precision rangeMax:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.MEDIUM_FLOAT).rangeMax), d.push("webgl vertex shader low float precision:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.LOW_FLOAT).precision), d.push("webgl vertex shader low float precision rangeMin:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.LOW_FLOAT).rangeMin), d.push("webgl vertex shader low float precision rangeMax:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.LOW_FLOAT).rangeMax), d.push("webgl fragment shader high float precision:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.HIGH_FLOAT).precision), d.push("webgl fragment shader high float precision rangeMin:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.HIGH_FLOAT).rangeMin), d.push("webgl fragment shader high float precision rangeMax:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.HIGH_FLOAT).rangeMax), d.push("webgl fragment shader medium float precision:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.MEDIUM_FLOAT).precision), d.push("webgl fragment shader medium float precision rangeMin:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.MEDIUM_FLOAT).rangeMin), d.push("webgl fragment shader medium float precision rangeMax:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.MEDIUM_FLOAT).rangeMax), d.push("webgl fragment shader low float precision:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.LOW_FLOAT).precision), d.push("webgl fragment shader low float precision rangeMin:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.LOW_FLOAT).rangeMin), d.push("webgl fragment shader low float precision rangeMax:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.LOW_FLOAT).rangeMax), d.push("webgl vertex shader high int precision:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.HIGH_INT).precision), d.push("webgl vertex shader high int precision rangeMin:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.HIGH_INT).rangeMin), d.push("webgl vertex shader high int precision rangeMax:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.HIGH_INT).rangeMax), d.push("webgl vertex shader medium int precision:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.MEDIUM_INT).precision), d.push("webgl vertex shader medium int precision rangeMin:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.MEDIUM_INT).rangeMin), d.push("webgl vertex shader medium int precision rangeMax:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.MEDIUM_INT).rangeMax), d.push("webgl vertex shader low int precision:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.LOW_INT).precision), d.push("webgl vertex shader low int precision rangeMin:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.LOW_INT).rangeMin), d.push("webgl vertex shader low int precision rangeMax:" + a.getShaderPrecisionFormat(a.VERTEX_SHADER, a.LOW_INT).rangeMax), d.push("webgl fragment shader high int precision:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.HIGH_INT).precision), d.push("webgl fragment shader high int precision rangeMin:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.HIGH_INT).rangeMin), d.push("webgl fragment shader high int precision rangeMax:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.HIGH_INT).rangeMax), d.push("webgl fragment shader medium int precision:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.MEDIUM_INT).precision), d.push("webgl fragment shader medium int precision rangeMin:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.MEDIUM_INT).rangeMin), d.push("webgl fragment shader medium int precision rangeMax:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.MEDIUM_INT).rangeMax), d.push("webgl fragment shader low int precision:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.LOW_INT).precision), d.push("webgl fragment shader low int precision rangeMin:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.LOW_INT).rangeMin), d.push("webgl fragment shader low int precision rangeMax:" + a.getShaderPrecisionFormat(a.FRAGMENT_SHADER, a.LOW_INT).rangeMax), d.join("~")) : ("undefined" == typeof NODEBUG && this.log("WebGL fingerprinting is incomplete, because your browser does not support getShaderPrecisionFormat"), d.join("~"))) : null
            },
            getAdBlock: function() {
                var a = document.createElement("div");
                a.setAttribute("id", "ads");
                try {
                    return document.body.appendChild(a),
                    document.getElementById("ads") ? !1 : !0
                } catch(b) {
                    return ! 1
                }
            },
            getHasLiedLanguages: function() {
                if ("undefined" != typeof navigator.languages) try {
                    var a = navigator.languages[0].substr(0, 2);
                    if (a !== navigator.language.substr(0, 2)) return ! 0
                } catch(b) {
                    return ! 0
                }
                return ! 1
            },
            getHasLiedResolution: function() {
                return screen.width < screen.availWidth ? !0 : screen.height < screen.availHeight ? !0 : !1
            },
            getHasLiedOs: function() {
                var d, e, a = navigator.userAgent.toLowerCase(),
                b = navigator.oscpu,
                c = navigator.platform.toLowerCase();
                if (d = a.indexOf("windows phone") >= 0 ? "Windows Phone": a.indexOf("win") >= 0 ? "Windows": a.indexOf("android") >= 0 ? "Android": a.indexOf("linux") >= 0 ? "Linux": a.indexOf("iphone") >= 0 || a.indexOf("ipad") >= 0 ? "iOS": a.indexOf("mac") >= 0 ? "Mac": "Other", e = "ontouchstart" in window || navigator.maxTouchPoints > 0 || navigator.msMaxTouchPoints > 0 ? !0 : !1, e && "Windows Phone" !== d && "Android" !== d && "iOS" !== d && "Other" !== d) return ! 0;
                if ("undefined" != typeof b) {
                    if (b = b.toLowerCase(), b.indexOf("win") >= 0 && "Windows" !== d && "Windows Phone" !== d) return ! 0;
                    if (b.indexOf("linux") >= 0 && "Linux" !== d && "Android" !== d) return ! 0;
                    if (b.indexOf("mac") >= 0 && "Mac" !== d && "iOS" !== d) return ! 0;
                    if (0 === b.indexOf("win") && 0 === b.indexOf("linux") && b.indexOf("mac") >= 0 && "other" !== d) return ! 0
                }
                return c.indexOf("win") >= 0 && "Windows" !== d && "Windows Phone" !== d ? !0 : (c.indexOf("linux") >= 0 || c.indexOf("android") >= 0 || c.indexOf("pike") >= 0) && "Linux" !== d && "Android" !== d ? !0 : (c.indexOf("mac") >= 0 || c.indexOf("ipad") >= 0 || c.indexOf("ipod") >= 0 || c.indexOf("iphone") >= 0) && "Mac" !== d && "iOS" !== d ? !0 : 0 === c.indexOf("win") && 0 === c.indexOf("linux") && c.indexOf("mac") >= 0 && "other" !== d ? !0 : "undefined" == typeof navigator.plugins && "Windows" !== d && "Windows Phone" !== d ? !0 : !1
            },
            getHasLiedBrowser: function() {
                var c, d, e, a = navigator.userAgent.toLowerCase(),
                b = navigator.productSub;
                if (c = a.indexOf("firefox") >= 0 ? "Firefox": a.indexOf("opera") >= 0 || a.indexOf("opr") >= 0 ? "Opera": a.indexOf("chrome") >= 0 ? "Chrome": a.indexOf("safari") >= 0 ? "Safari": a.indexOf("trident") >= 0 ? "Internet Explorer": "Other", ("Chrome" === c || "Safari" === c || "Opera" === c) && "20030107" !== b) return ! 0;
                if (d = eval.toString().length, 37 === d && "Safari" !== c && "Firefox" !== c && "Other" !== c) return ! 0;
                if (39 === d && "Internet Explorer" !== c && "Other" !== c) return ! 0;
                if (33 === d && "Chrome" !== c && "Opera" !== c && "Other" !== c) return ! 0;
                try {
                    throw "a"
                } catch(f) {
                    try {
                        f.toSource(),
                        e = !0
                    } catch(g) {
                        e = !1
                    }
                }
                return e && "Firefox" !== c && "Other" !== c ? !0 : !1
            },
            isCanvasSupported: function() {
                var a = document.createElement("canvas");
                return ! (!a.getContext || !a.getContext("2d"))
            },
            isWebGlSupported: function() {
                if (!this.isCanvasSupported()) return ! 1;
                var b, a = document.createElement("canvas");
                try {
                    b = a.getContext && (a.getContext("webgl") || a.getContext("experimental-webgl"))
                } catch(c) {
                    b = !1
                }
                return !! window.WebGLRenderingContext && !!b
            },
            isIE: function() {
                return "Microsoft Internet Explorer" === navigator.appName ? !0 : "Netscape" === navigator.appName && /Trident/.test(navigator.userAgent) ? !0 : !1
            },
            hasSwfObjectLoaded: function() {
                return "undefined" != typeof window.swfobject
            },
            hasMinFlashInstalled: function() {
                return B.hasFlashPlayerVersion("9.0.0")
            },
            addFlashDivNode: function() {
                var a = document.createElement("div");
                a.setAttribute("id", this.options.swfContainerId),
                document.body.appendChild(a)
            },
            loadSwfAndDetectFonts: function(a) {
                var c, d, e, b = "___fp_swf_loaded";
                window[b] = function(b) {
                    a(b)
                },
                c = this.options.swfContainerId,
                this.addFlashDivNode(),
                d = {
                    onReady: b
                },
                e = {
                    allowScriptAccess: "always",
                    menu: "false"
                },
                B.embedSWF(this.options.swfPath, c, "1", "1", "9.0.0", !1, d, e, {})
            },
            getWebglCanvas: function() {
                var a = document.createElement("canvas"),
                b = null;
                try {
                    b = a.getContext("webgl") || a.getContext("experimental-webgl")
                } catch(c) {}
                return b || (b = null),
                b
            },
            each: function(a, b, c) {
                var d, e, f;
                if (null !== a) if (this.nativeForEach && a.forEach === this.nativeForEach) a.forEach(b, c);
                else if (a.length === +a.length) {
                    for (d = 0, e = a.length; e > d; d++) if (b.call(c, a[d], d, a) === {}) return
                } else for (f in a) if (a.hasOwnProperty(f) && b.call(c, a[f], f, a) === {}) return
            },
            map: function(a, b, c) {
                var d = [];
                return null == a ? d: this.nativeMap && a.map === this.nativeMap ? a.map(b, c) : (this.each(a,
                function(a, e, f) {
                    d[d.length] = b.call(c, a, e, f)
                }), d)
            },
            x64Add: function(a, b) {
                a = [a[0] >>> 16, 65535 & a[0], a[1] >>> 16, 65535 & a[1]],
                b = [b[0] >>> 16, 65535 & b[0], b[1] >>> 16, 65535 & b[1]];
                var c = [0, 0, 0, 0];
                return c[3] += a[3] + b[3],
                c[2] += c[3] >>> 16,
                c[3] &= 65535,
                c[2] += a[2] + b[2],
                c[1] += c[2] >>> 16,
                c[2] &= 65535,
                c[1] += a[1] + b[1],
                c[0] += c[1] >>> 16,
                c[1] &= 65535,
                c[0] += a[0] + b[0],
                c[0] &= 65535,
                [c[0] << 16 | c[1], c[2] << 16 | c[3]]
            },
            x64Multiply: function(a, b) {
                a = [a[0] >>> 16, 65535 & a[0], a[1] >>> 16, 65535 & a[1]],
                b = [b[0] >>> 16, 65535 & b[0], b[1] >>> 16, 65535 & b[1]];
                var c = [0, 0, 0, 0];
                return c[3] += a[3] * b[3],
                c[2] += c[3] >>> 16,
                c[3] &= 65535,
                c[2] += a[2] * b[3],
                c[1] += c[2] >>> 16,
                c[2] &= 65535,
                c[2] += a[3] * b[2],
                c[1] += c[2] >>> 16,
                c[2] &= 65535,
                c[1] += a[1] * b[3],
                c[0] += c[1] >>> 16,
                c[1] &= 65535,
                c[1] += a[2] * b[2],
                c[0] += c[1] >>> 16,
                c[1] &= 65535,
                c[1] += a[3] * b[1],
                c[0] += c[1] >>> 16,
                c[1] &= 65535,
                c[0] += a[0] * b[3] + a[1] * b[2] + a[2] * b[1] + a[3] * b[0],
                c[0] &= 65535,
                [c[0] << 16 | c[1], c[2] << 16 | c[3]]
            },
            x64Rotl: function(a, b) {
                return b %= 64,
                32 === b ? [a[1], a[0]] : 32 > b ? [a[0] << b | a[1] >>> 32 - b, a[1] << b | a[0] >>> 32 - b] : (b -= 32, [a[1] << b | a[0] >>> 32 - b, a[0] << b | a[1] >>> 32 - b])
            },
            x64LeftShift: function(a, b) {
                return b %= 64,
                0 === b ? a: 32 > b ? [a[0] << b | a[1] >>> 32 - b, a[1] << b] : [a[1] << b - 32, 0]
            },
            x64Xor: function(a, b) {
                return [a[0] ^ b[0], a[1] ^ b[1]]
            },
            x64Fmix: function(a) {
                return a = this.x64Xor(a, [0, a[0] >>> 1]),
                a = this.x64Multiply(a, [4283543511, 3981806797]),
                a = this.x64Xor(a, [0, a[0] >>> 1]),
                a = this.x64Multiply(a, [3301882366, 444984403]),
                a = this.x64Xor(a, [0, a[0] >>> 1])
            },
            x64hash128: function(a, b) {
                var c, d, e, f, g, h, i, j, k;
                for (a = a || "", b = b || 0, c = a.length % 16, d = a.length - c, e = [0, b], f = [0, b], g = [0, 0], h = [0, 0], i = [2277735313, 289559509], j = [1291169091, 658871167], k = 0; d > k; k += 16) g = [255 & a.charCodeAt(k + 4) | (255 & a.charCodeAt(k + 5)) << 8 | (255 & a.charCodeAt(k + 6)) << 16 | (255 & a.charCodeAt(k + 7)) << 24, 255 & a.charCodeAt(k) | (255 & a.charCodeAt(k + 1)) << 8 | (255 & a.charCodeAt(k + 2)) << 16 | (255 & a.charCodeAt(k + 3)) << 24],
                h = [255 & a.charCodeAt(k + 12) | (255 & a.charCodeAt(k + 13)) << 8 | (255 & a.charCodeAt(k + 14)) << 16 | (255 & a.charCodeAt(k + 15)) << 24, 255 & a.charCodeAt(k + 8) | (255 & a.charCodeAt(k + 9)) << 8 | (255 & a.charCodeAt(k + 10)) << 16 | (255 & a.charCodeAt(k + 11)) << 24],
                g = this.x64Multiply(g, i),
                g = this.x64Rotl(g, 31),
                g = this.x64Multiply(g, j),
                e = this.x64Xor(e, g),
                e = this.x64Rotl(e, 27),
                e = this.x64Add(e, f),
                e = this.x64Add(this.x64Multiply(e, [0, 5]), [0, 1390208809]),
                h = this.x64Multiply(h, j),
                h = this.x64Rotl(h, 33),
                h = this.x64Multiply(h, i),
                f = this.x64Xor(f, h),
                f = this.x64Rotl(f, 31),
                f = this.x64Add(f, e),
                f = this.x64Add(this.x64Multiply(f, [0, 5]), [0, 944331445]);
                switch (g = [0, 0], h = [0, 0], c) {
                case 15:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(k + 14)], 48));
                case 14:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(k + 13)], 40));
                case 13:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(k + 12)], 32));
                case 12:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(k + 11)], 24));
                case 11:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(k + 10)], 16));
                case 10:
                    h = this.x64Xor(h, this.x64LeftShift([0, a.charCodeAt(k + 9)], 8));
                case 9:
                    h = this.x64Xor(h, [0, a.charCodeAt(k + 8)]),
                    h = this.x64Multiply(h, j),
                    h = this.x64Rotl(h, 33),
                    h = this.x64Multiply(h, i),
                    f = this.x64Xor(f, h);
                case 8:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 7)], 56));
                case 7:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 6)], 48));
                case 6:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 5)], 40));
                case 5:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 4)], 32));
                case 4:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 3)], 24));
                case 3:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 2)], 16));
                case 2:
                    g = this.x64Xor(g, this.x64LeftShift([0, a.charCodeAt(k + 1)], 8));
                case 1:
                    g = this.x64Xor(g, [0, a.charCodeAt(k)]),
                    g = this.x64Multiply(g, i),
                    g = this.x64Rotl(g, 31),
                    g = this.x64Multiply(g, j),
                    e = this.x64Xor(e, g)
                }
                return e = this.x64Xor(e, [0, a.length]),
                f = this.x64Xor(f, [0, a.length]),
                e = this.x64Add(e, f),
                f = this.x64Add(f, e),
                e = this.x64Fmix(e),
                f = this.x64Fmix(f),
                e = this.x64Add(e, f),
                f = this.x64Add(f, e),
                ("00000000" + (e[0] >>> 0).toString(16)).slice( - 8) + ("00000000" + (e[1] >>> 0).toString(16)).slice( - 8) + ("00000000" + (f[0] >>> 0).toString(16)).slice( - 8) + ("00000000" + (f[1] >>> 0).toString(16)).slice( - 8)
            }
        },
        a.VERSION = "1.0.3",
        a
    }),
    a = a ||
    function(a, b) {
        var m, c = {},
        d = c.lib = {},
        e = function() {},
        f = d.Base = {
            extend: function(a) {
                e.prototype = this;
                var b = new e;
                return a && b.mixIn(a),
                b.hasOwnProperty("init") || (b.init = function() {
                    b.$super.init.apply(this, arguments)
                }),
                b.init.prototype = b,
                b.$super = this,
                b
            },
            create: function() {
                var a = this.extend();
                return a.init.apply(a, arguments),
                a
            },
            init: function() {},
            mixIn: function(a) {
                for (var b in a) a.hasOwnProperty(b) && (this[b] = a[b]);
                a.hasOwnProperty("toString") && (this.toString = a.toString)
            },
            clone: function() {
                return this.init.prototype.extend(this)
            }
        },
        g = d.WordArray = f.extend({
            init: function(a, c) {
                a = this.words = a || [],
                this.sigBytes = c != b ? c: 4 * a.length
            },
            toString: function(a) {
                return (a || i).stringify(this)
            },
            concat: function(a) {
                var e, b = this.words,
                c = a.words,
                d = this.sigBytes;
                if (a = a.sigBytes, this.clamp(), d % 4) for (e = 0; a > e; e++) b[d + e >>> 2] |= (255 & c[e >>> 2] >>> 24 - 8 * (e % 4)) << 24 - 8 * ((d + e) % 4);
                else if (65535 < c.length) for (e = 0; a > e; e += 4) b[d + e >>> 2] = c[e >>> 2];
                else b.push.apply(b, c);
                return this.sigBytes += a,
                this
            },
            clamp: function() {
                var b = this.words,
                c = this.sigBytes;
                b[c >>> 2] &= 4294967295 << 32 - 8 * (c % 4),
                b.length = a.ceil(c / 4)
            },
            clone: function() {
                var a = f.clone.call(this);
                return a.words = this.words.slice(0),
                a
            },
            random: function(b) {
                for (var c = [], d = 0; b > d; d += 4) c.push(0 | 4294967296 * a.random());
                return new g.init(c, b)
            }
        }),
        h = c.enc = {},
        i = h.Hex = {
            stringify: function(a) {
                var c, d, e, b = a.words;
                for (a = a.sigBytes, c = [], d = 0; a > d; d++) e = 255 & b[d >>> 2] >>> 24 - 8 * (d % 4),
                c.push((e >>> 4).toString(16)),
                c.push((15 & e).toString(16));
                return c.join("")
            },
            parse: function(a) {
                for (var b = a.length,
                c = [], d = 0; b > d; d += 2) c[d >>> 3] |= parseInt(a.substr(d, 2), 16) << 24 - 4 * (d % 8);
                return new g.init(c, b / 2)
            }
        },
        j = h.Latin1 = {
            stringify: function(a) {
                var c, d, b = a.words;
                for (a = a.sigBytes, c = [], d = 0; a > d; d++) c.push(String.fromCharCode(255 & b[d >>> 2] >>> 24 - 8 * (d % 4)));
                return c.join("")
            },
            parse: function(a) {
                for (var b = a.length,
                c = [], d = 0; b > d; d++) c[d >>> 2] |= (255 & a.charCodeAt(d)) << 24 - 8 * (d % 4);
                return new g.init(c, b)
            }
        },
        k = h.Utf8 = {
            stringify: function(a) {
                try {
                    return decodeURIComponent(escape(j.stringify(a)))
                } catch(b) {
                    throw Error("Malformed UTF-8 data")
                }
            },
            parse: function(a) {
                return j.parse(unescape(encodeURIComponent(a)))
            }
        },
        l = d.BufferedBlockAlgorithm = f.extend({
            reset: function() {
                this._data = new g.init,
                this._nDataBytes = 0
            },
            _append: function(a) {
                "string" == typeof a && (a = k.parse(a)),
                this._data.concat(a),
                this._nDataBytes += a.sigBytes
            },
            _process: function(b) {
                var i, c = this._data,
                d = c.words,
                e = c.sigBytes,
                f = this.blockSize,
                h = e / (4 * f);
                if (h = b ? a.ceil(h) : a.max((0 | h) - this._minBufferSize, 0), b = h * f, e = a.min(4 * b, e), b) {
                    for (i = 0; b > i; i += f) this._doProcessBlock(d, i);
                    i = d.splice(0, b),
                    c.sigBytes -= e
                }
                return new g.init(i, e)
            },
            clone: function() {
                var a = f.clone.call(this);
                return a._data = this._data.clone(),
                a
            },
            _minBufferSize: 0
        });
        return d.Hasher = l.extend({
            cfg: f.extend(),
            init: function(a) {
                this.cfg = this.cfg.extend(a),
                this.reset()
            },
            reset: function() {
                l.reset.call(this),
                this._doReset()
            },
            update: function(a) {
                return this._append(a),
                this._process(),
                this
            },
            finalize: function(a) {
                return a && this._append(a),
                this._doFinalize()
            },
            blockSize: 16,
            _createHelper: function(a) {
                return function(b, c) {
                    return new a.init(c).finalize(b)
                }
            },
            _createHmacHelper: function(a) {
                return function(b, c) {
                    return new m.HMAC.init(a, c).finalize(b)
                }
            }
        }),
        m = c.algo = {},
        c
    } (Math),
    function() {
        var b = a,
        c = b.lib,
        d = c.WordArray,
        e = c.Hasher,
        f = [],
        c = b.algo.SHA1 = e.extend({
            _doReset: function() {
                this._hash = new d.init([1732584193, 4023233417, 2562383102, 271733878, 3285377520])
            },
            _doProcessBlock: function(a, b) {
                var c, d, e, g, h, i, j, k;
                for (c = this._hash.words, d = c[0], e = c[1], g = c[2], h = c[3], i = c[4], j = 0; 80 > j; j++) 16 > j ? f[j] = 0 | a[b + j] : (k = f[j - 3] ^ f[j - 8] ^ f[j - 14] ^ f[j - 16], f[j] = k << 1 | k >>> 31),
                k = (d << 5 | d >>> 27) + i + f[j],
                k = 20 > j ? k + ((e & g | ~e & h) + 1518500249) : 40 > j ? k + ((e ^ g ^ h) + 1859775393) : 60 > j ? k + ((e & g | e & h | g & h) - 1894007588) : k + ((e ^ g ^ h) - 899497514),
                i = h,
                h = g,
                g = e << 30 | e >>> 2,
                e = d,
                d = k;
                c[0] = 0 | c[0] + d,
                c[1] = 0 | c[1] + e,
                c[2] = 0 | c[2] + g,
                c[3] = 0 | c[3] + h,
                c[4] = 0 | c[4] + i
            },
            _doFinalize: function() {
                var a = this._data,
                b = a.words,
                c = 8 * this._nDataBytes,
                d = 8 * a.sigBytes;
                return b[d >>> 5] |= 128 << 24 - d % 32,
                b[(d + 64 >>> 9 << 4) + 14] = Math.floor(c / 4294967296),
                b[(d + 64 >>> 9 << 4) + 15] = c,
                a.sigBytes = 4 * b.length,
                this._process(),
                this._hash
            },
            clone: function() {
                var a = e.clone.call(this);
                return a._hash = this._hash.clone(),
                a
            }
        });
        b.SHA1 = e._createHelper(c),
        b.HmacSHA1 = e._createHmacHelper(c)
    } (),
    function() {
        var b = a,
        c = b.enc.Utf8;
        b.algo.HMAC = b.lib.Base.extend({
            init: function(a, b) {
                var d, e, f, g, h, i, j;
                for (a = this._hasher = new a.init, "string" == typeof b && (b = c.parse(b)), d = a.blockSize, e = 4 * d, b.sigBytes > e && (b = a.finalize(b)), b.clamp(), f = this._oKey = b.clone(), g = this._iKey = b.clone(), h = f.words, i = g.words, j = 0; d > j; j++) h[j] ^= 1549556828,
                i[j] ^= 909522486;
                f.sigBytes = g.sigBytes = e,
                this.reset()
            },
            reset: function() {
                var a = this._hasher;
                a.reset(),
                a.update(this._iKey)
            },
            update: function(a) {
                return this._hasher.update(a),
                this
            },
            finalize: function(a) {
                var b = this._hasher;
                return a = b.finalize(a),
                b.reset(),
                b.finalize(this._oKey.clone().concat(a))
            }
        })
    } (),
    a = a ||
    function(a, b) {
        var m, c = {},
        d = c.lib = {},
        e = function() {},
        f = d.Base = {
            extend: function(a) {
                e.prototype = this;
                var b = new e;
                return a && b.mixIn(a),
                b.hasOwnProperty("init") || (b.init = function() {
                    b.$super.init.apply(this, arguments)
                }),
                b.init.prototype = b,
                b.$super = this,
                b
            },
            create: function() {
                var a = this.extend();
                return a.init.apply(a, arguments),
                a
            },
            init: function() {},
            mixIn: function(a) {
                for (var b in a) a.hasOwnProperty(b) && (this[b] = a[b]);
                a.hasOwnProperty("toString") && (this.toString = a.toString)
            },
            clone: function() {
                return this.init.prototype.extend(this)
            }
        },
        g = d.WordArray = f.extend({
            init: function(a, c) {
                a = this.words = a || [],
                this.sigBytes = c != b ? c: 4 * a.length
            },
            toString: function(a) {
                return (a || i).stringify(this)
            },
            concat: function(a) {
                var e, b = this.words,
                c = a.words,
                d = this.sigBytes;
                if (a = a.sigBytes, this.clamp(), d % 4) for (e = 0; a > e; e++) b[d + e >>> 2] |= (255 & c[e >>> 2] >>> 24 - 8 * (e % 4)) << 24 - 8 * ((d + e) % 4);
                else if (65535 < c.length) for (e = 0; a > e; e += 4) b[d + e >>> 2] = c[e >>> 2];
                else b.push.apply(b, c);
                return this.sigBytes += a,
                this
            },
            clamp: function() {
                var b = this.words,
                c = this.sigBytes;
                b[c >>> 2] &= 4294967295 << 32 - 8 * (c % 4),
                b.length = a.ceil(c / 4)
            },
            clone: function() {
                var a = f.clone.call(this);
                return a.words = this.words.slice(0),
                a
            },
            random: function(b) {
                for (var c = [], d = 0; b > d; d += 4) c.push(0 | 4294967296 * a.random());
                return new g.init(c, b)
            }
        }),
        h = c.enc = {},
        i = h.Hex = {
            stringify: function(a) {
                var c, d, e, b = a.words;
                for (a = a.sigBytes, c = [], d = 0; a > d; d++) e = 255 & b[d >>> 2] >>> 24 - 8 * (d % 4),
                c.push((e >>> 4).toString(16)),
                c.push((15 & e).toString(16));
                return c.join("")
            },
            parse: function(a) {
                for (var b = a.length,
                c = [], d = 0; b > d; d += 2) c[d >>> 3] |= parseInt(a.substr(d, 2), 16) << 24 - 4 * (d % 8);
                return new g.init(c, b / 2)
            }
        },
        j = h.Latin1 = {
            stringify: function(a) {
                var c, d, b = a.words;
                for (a = a.sigBytes, c = [], d = 0; a > d; d++) c.push(String.fromCharCode(255 & b[d >>> 2] >>> 24 - 8 * (d % 4)));
                return c.join("")
            },
            parse: function(a) {
                for (var b = a.length,
                c = [], d = 0; b > d; d++) c[d >>> 2] |= (255 & a.charCodeAt(d)) << 24 - 8 * (d % 4);
                return new g.init(c, b)
            }
        },
        k = h.Utf8 = {
            stringify: function(a) {
                try {
                    return decodeURIComponent(escape(j.stringify(a)))
                } catch(b) {
                    throw Error("Malformed UTF-8 data")
                }
            },
            parse: function(a) {
                return j.parse(unescape(encodeURIComponent(a)))
            }
        },
        l = d.BufferedBlockAlgorithm = f.extend({
            reset: function() {
                this._data = new g.init,
                this._nDataBytes = 0
            },
            _append: function(a) {
                "string" == typeof a && (a = k.parse(a)),
                this._data.concat(a),
                this._nDataBytes += a.sigBytes
            },
            _process: function(b) {
                var i, c = this._data,
                d = c.words,
                e = c.sigBytes,
                f = this.blockSize,
                h = e / (4 * f);
                if (h = b ? a.ceil(h) : a.max((0 | h) - this._minBufferSize, 0), b = h * f, e = a.min(4 * b, e), b) {
                    for (i = 0; b > i; i += f) this._doProcessBlock(d, i);
                    i = d.splice(0, b),
                    c.sigBytes -= e
                }
                return new g.init(i, e)
            },
            clone: function() {
                var a = f.clone.call(this);
                return a._data = this._data.clone(),
                a
            },
            _minBufferSize: 0
        });
        return d.Hasher = l.extend({
            cfg: f.extend(),
            init: function(a) {
                this.cfg = this.cfg.extend(a),
                this.reset()
            },
            reset: function() {
                l.reset.call(this),
                this._doReset()
            },
            update: function(a) {
                return this._append(a),
                this._process(),
                this
            },
            finalize: function(a) {
                return a && this._append(a),
                this._doFinalize()
            },
            blockSize: 16,
            _createHelper: function(a) {
                return function(b, c) {
                    return new a.init(c).finalize(b)
                }
            },
            _createHmacHelper: function(a) {
                return function(b, c) {
                    return new m.HMAC.init(a, c).finalize(b)
                }
            }
        }),
        m = c.algo = {},
        c
    } (Math),
    function(b) {
        var c, d, e, f, g, h, i, j, k, l, m, n, o;
        for (c = a, d = c.lib, e = d.WordArray, f = d.Hasher, d = c.algo, g = [], h = [], i = function(a) {
            return 0 | 4294967296 * (a - (0 | a))
        },
        j = 2, k = 0; 64 > k;) {
            a: {
                for (l = j, m = b.sqrt(l), n = 2; m >= n; n++) if (! (l % n)) {
                    l = !1;
                    break a
                }
                l = !0
            }
            l && (8 > k && (g[k] = i(b.pow(j, .5))), h[k] = i(b.pow(j, 1 / 3)), k++),
            j++
        }
        o = [],
        d = d.SHA256 = f.extend({
            _doReset: function() {
                this._hash = new e.init(g.slice(0))
            },
            _doProcessBlock: function(a, b) {
                var c, d, e, f, g, i, j, k, l, m, n, p;
                for (c = this._hash.words, d = c[0], e = c[1], f = c[2], g = c[3], i = c[4], j = c[5], k = c[6], l = c[7], m = 0; 64 > m; m++) 16 > m ? o[m] = 0 | a[b + m] : (n = o[m - 15], p = o[m - 2], o[m] = ((n << 25 | n >>> 7) ^ (n << 14 | n >>> 18) ^ n >>> 3) + o[m - 7] + ((p << 15 | p >>> 17) ^ (p << 13 | p >>> 19) ^ p >>> 10) + o[m - 16]),
                n = l + ((i << 26 | i >>> 6) ^ (i << 21 | i >>> 11) ^ (i << 7 | i >>> 25)) + (i & j ^ ~i & k) + h[m] + o[m],
                p = ((d << 30 | d >>> 2) ^ (d << 19 | d >>> 13) ^ (d << 10 | d >>> 22)) + (d & e ^ d & f ^ e & f),
                l = k,
                k = j,
                j = i,
                i = 0 | g + n,
                g = f,
                f = e,
                e = d,
                d = 0 | n + p;
                c[0] = 0 | c[0] + d,
                c[1] = 0 | c[1] + e,
                c[2] = 0 | c[2] + f,
                c[3] = 0 | c[3] + g,
                c[4] = 0 | c[4] + i,
                c[5] = 0 | c[5] + j,
                c[6] = 0 | c[6] + k,
                c[7] = 0 | c[7] + l
            },
            _doFinalize: function() {
                var a = this._data,
                c = a.words,
                d = 8 * this._nDataBytes,
                e = 8 * a.sigBytes;
                return c[e >>> 5] |= 128 << 24 - e % 32,
                c[(e + 64 >>> 9 << 4) + 14] = b.floor(d / 4294967296),
                c[(e + 64 >>> 9 << 4) + 15] = d,
                a.sigBytes = 4 * c.length,
                this._process(),
                this._hash
            },
            clone: function() {
                var a = f.clone.call(this);
                return a._hash = this._hash.clone(),
                a
            }
        }),
        c.SHA256 = f._createHelper(d),
        c.HmacSHA256 = f._createHmacHelper(d)
    } (Math),
    c = function(a) {
        var b, c, d;
        if (!a) return "";
        if (G(a)) return a.replace(/\s/g, "");
        if ( - 1 != a.indexOf("://") && (a = a.substr(a.indexOf("://") + 3)), b = ["com", "net", "org", "gov", "edu", "mil", "biz", "name", "info", "mobi", "pro", "travel", "museum", "int", "areo", "post", "rec"], c = a.split("."), c.length <= 1) return a;
        if (!isNaN(c[c.length - 1])) return a;
        for (d = 0; d < b.length && b[d] != c[c.length - 1];) d++;
        if (d != b.length) return "." + c[c.length - 2] + "." + c[c.length - 1];
        for (d = 0; d < b.length && b[d] != c[c.length - 2];) d++;
        return d == b.length ? c[c.length - 2] + "." + c[c.length - 1] : "." + c[c.length - 3] + "." + c[c.length - 2] + "." + c[c.length - 1]
    },
    B = function() {
        function A() {
            var a, c, d;
            if (!t) {
                try {
                    a = i.getElementsByTagName("body")[0].appendChild(R("span")),
                    a.parentNode.removeChild(a)
                } catch(b) {
                    return
                }
                for (t = !0, c = l.length, d = 0; c > d; d++) l[d]()
            }
        }
        function C(a) {
            t ? a() : l[l.length] = a
        }
        function D(b) {
            if (typeof h.addEventListener != a) h.addEventListener("load", b, !1);
            else if (typeof i.addEventListener != a) i.addEventListener("load", b, !1);
            else if (typeof h.attachEvent != a) S(h, "onload", b);
            else if ("function" == typeof h.onload) {
                var c = h.onload;
                h.onload = function() {
                    c(),
                    b()
                }
            } else h.onload = b
        }
        function E() {
            k ? F() : G()
        }
        function F() {
            var f, g, c = i.getElementsByTagName("body")[0],
            d = R(b);
            d.setAttribute("type", e),
            f = c.appendChild(d),
            f ? (g = 0,
            function() {
                if (typeof f.GetVariable != a) {
                    var b = f.GetVariable("$version");
                    b && (b = b.split(" ")[1].split(","), y.pv = [parseInt(b[0], 10), parseInt(b[1], 10), parseInt(b[2], 10)])
                } else if (10 > g) return g++,
                setTimeout(arguments.callee, 10),
                void 0;
                c.removeChild(d),
                f = null,
                G()
            } ()) : G()
        }
        function G() {
            var c, d, e, f, g, h, i, j, k, l, n, b = m.length;
            if (b > 0) for (c = 0; b > c; c++) if (d = m[c].id, e = m[c].callbackFn, f = {
                success: !1,
                id: d
            },
            y.pv[0] > 0) {
                if (g = Q(d)) if (!T(m[c].swfVersion) || y.wk && y.wk < 312) if (m[c].expressInstall && I()) {
                    for (h = {},
                    h.data = m[c].expressInstall, h.width = g.getAttribute("width") || "0", h.height = g.getAttribute("height") || "0", g.getAttribute("class") && (h.styleclass = g.getAttribute("class")), g.getAttribute("align") && (h.align = g.getAttribute("align")), i = {},
                    j = g.getElementsByTagName("param"), k = j.length, l = 0; k > l; l++)"movie" != j[l].getAttribute("name").toLowerCase() && (i[j[l].getAttribute("name")] = j[l].getAttribute("value"));
                    J(h, i, d, e)
                } else K(g),
                e && e(f);
                else V(d, !0),
                e && (f.success = !0, f.ref = H(d), e(f))
            } else V(d, !0),
            e && (n = H(d), n && typeof n.SetVariable != a && (f.success = !0, f.ref = n), e(f))
        }
        function H(c) {
            var f, d = null,
            e = Q(c);
            return e && "OBJECT" == e.nodeName && (typeof e.SetVariable != a ? d = e: (f = e.getElementsByTagName(b)[0], f && (d = f))),
            d
        }
        function I() {
            return ! u && T("6.0.65") && (y.win || y.mac) && !(y.wk && y.wk < 312)
        }
        function J(b, c, d, e) {
            var g, j, k, l;
            u = !0,
            r = e || null,
            s = {
                success: !1,
                id: d
            },
            g = Q(d),
            g && ("OBJECT" == g.nodeName ? (p = L(g), q = null) : (p = g, q = d), b.id = f, (typeof b.width == a || !/%$/.test(b.width) && parseInt(b.width, 10) < 310) && (b.width = "310"), (typeof b.height == a || !/%$/.test(b.height) && parseInt(b.height, 10) < 137) && (b.height = "137"), i.title = i.title.slice(0, 47) + " - Flash Player Installation", j = y.ie && y.win ? "ActiveX": "PlugIn", k = "MMredirectURL=" + h.location.toString().replace(/&/g, "%26") + "&MMplayerType=" + j + "&MMdoctitle=" + i.title, typeof c.flashvars != a ? c.flashvars += "&" + k: c.flashvars = k, y.ie && y.win && 4 != g.readyState && (l = R("div"), d += "SWFObjectNew", l.setAttribute("id", d), g.parentNode.insertBefore(l, g), g.style.display = "none",
            function() {
                4 == g.readyState ? g.parentNode.removeChild(g) : setTimeout(arguments.callee, 10)
            } ()), M(b, c, d))
        }
        function K(a) {
            if (y.ie && y.win && 4 != a.readyState) {
                var b = R("div");
                a.parentNode.insertBefore(b, a),
                b.parentNode.replaceChild(L(a), b),
                a.style.display = "none",
                function() {
                    4 == a.readyState ? a.parentNode.removeChild(a) : setTimeout(arguments.callee, 10)
                } ()
            } else a.parentNode.replaceChild(L(a), a)
        }
        function L(a) {
            var d, e, f, g, c = R("div");
            if (y.win && y.ie) c.innerHTML = a.innerHTML;
            else if (d = a.getElementsByTagName(b)[0], d && (e = d.childNodes)) for (f = e.length, g = 0; f > g; g++) 1 == e[g].nodeType && "PARAM" == e[g].nodeName || 8 == e[g].nodeType || c.appendChild(e[g].cloneNode(!0));
            return c
        }
        function M(c, d, f) {
            var g, i, j, k, l, m, o, p, h = Q(f);
            if (y.wk && y.wk < 312) return g;
            if (h) if (typeof c.id == a && (c.id = f), y.ie && y.win) {
                i = "";
                for (j in c) c[j] != Object.prototype[j] && ("data" == j.toLowerCase() ? d.movie = c[j] : "styleclass" == j.toLowerCase() ? i += ' class="' + c[j] + '"': "classid" != j.toLowerCase() && (i += " " + j + '="' + c[j] + '"'));
                k = "";
                for (l in d) d[l] != Object.prototype[l] && (k += '<param name="' + l + '" value="' + d[l] + '" />');
                h.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + i + ">" + k + "</object>",
                n[n.length] = c.id,
                g = Q(c.id)
            } else {
                m = R(b),
                m.setAttribute("type", e);
                for (o in c) c[o] != Object.prototype[o] && ("styleclass" == o.toLowerCase() ? m.setAttribute("class", c[o]) : "classid" != o.toLowerCase() && m.setAttribute(o, c[o]));
                for (p in d) d[p] != Object.prototype[p] && "movie" != p.toLowerCase() && N(m, p, d[p]);
                h.parentNode.replaceChild(m, h),
                g = m
            }
            return g
        }
        function N(a, b, c) {
            var d = R("param");
            d.setAttribute("name", b),
            d.setAttribute("value", c),
            a.appendChild(d)
        }
        function O(a) {
            var b = Q(a);
            b && "OBJECT" == b.nodeName && (y.ie && y.win ? (b.style.display = "none",
            function() {
                4 == b.readyState ? P(a) : setTimeout(arguments.callee, 10)
            } ()) : b.parentNode.removeChild(b))
        }
        function P(a) {
            var c, b = Q(a);
            if (b) {
                for (c in b)"function" == typeof b[c] && (b[c] = null);
                b.parentNode.removeChild(b)
            }
        }
        function Q(a) {
            var b = null;
            try {
                b = i.getElementById(a)
            } catch(c) {}
            return b
        }
        function R(a) {
            return i.createElement(a)
        }
        function S(a, b, c) {
            a.attachEvent(b, c),
            o[o.length] = [a, b, c]
        }
        function T(a) {
            var b = y.pv,
            c = a.split(".");
            return c[0] = parseInt(c[0], 10),
            c[1] = parseInt(c[1], 10) || 0,
            c[2] = parseInt(c[2], 10) || 0,
            b[0] > c[0] || b[0] == c[0] && b[1] > c[1] || b[0] == c[0] && b[1] == c[1] && b[2] >= c[2] ? !0 : !1
        }
        function U(c, d, e, f) {
            var g, h, j;
            y.ie && y.mac || (g = i.getElementsByTagName("head")[0], g && (h = e && "string" == typeof e ? e: "screen", f && (v = null, w = null), v && w == h || (j = R("style"), j.setAttribute("type", "text/css"), j.setAttribute("media", h), v = g.appendChild(j), y.ie && y.win && typeof i.styleSheets != a && i.styleSheets.length > 0 && (v = i.styleSheets[i.styleSheets.length - 1]), w = h), y.ie && y.win ? v && typeof v.addRule == b && v.addRule(c, d) : v && typeof i.createTextNode != a && v.appendChild(i.createTextNode(c + " {" + d + "}"))))
        }
        function V(a, b) {
            if (x) {
                var c = b ? "visible": "hidden";
                t && Q(a) ? Q(a).style.visibility = c: U("#" + a, "visibility:" + c)
            }
        }
        function W(b) {
            var c = /[\\\"<>\.;]/,
            d = null != c.exec(b);
            return d && typeof encodeURIComponent != a ? encodeURIComponent(b) : b
        }
        var p, q, r, s, v, w, a = "undefined",
        b = "object",
        c = "Shockwave Flash",
        d = "ShockwaveFlash.ShockwaveFlash",
        e = "application/x-shockwave-flash",
        f = "SWFObjectExprInst",
        g = "onreadystatechange",
        h = window,
        i = document,
        j = navigator,
        k = !1,
        l = [E],
        m = [],
        n = [],
        o = [],
        t = !1,
        u = !1,
        x = !0,
        y = function() {
            var s, f = typeof i.getElementById != a && typeof i.getElementsByTagName != a && typeof i.createElement != a,
            g = j.userAgent.toLowerCase(),
            l = j.platform.toLowerCase(),
            m = l ? /win/.test(l) : /win/.test(g),
            n = l ? /mac/.test(l) : /mac/.test(g),
            o = /webkit/.test(g) ? parseFloat(g.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : !1,
            p = !1,
            q = [0, 0, 0],
            r = null;
            if (typeof j.plugins != a && typeof j.plugins[c] == b) r = j.plugins[c].description,
            !r || typeof j.mimeTypes != a && j.mimeTypes[e] && !j.mimeTypes[e].enabledPlugin || (k = !0, p = !1, r = r.replace(/^.*\s+(\S+\s+\S+$)/, "$1"), q[0] = parseInt(r.replace(/^(.*)\..*$/, "$1"), 10), q[1] = parseInt(r.replace(/^.*\.(.*)\s.*$/, "$1"), 10), q[2] = /[a-zA-Z]/.test(r) ? parseInt(r.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0);
            else if (typeof h.ActiveXObject != a) try {
                s = new ActiveXObject(d),
                s && (r = s.GetVariable("$version"), r && (p = !0, r = r.split(" ")[1].split(","), q = [parseInt(r[0], 10), parseInt(r[1], 10), parseInt(r[2], 10)]))
            } catch(t) {}
            return {
                w3: f,
                pv: q,
                wk: o,
                ie: p,
                win: m,
                mac: n
            }
        } ();
        return function() {
            y.w3 && ((typeof i.readyState != a && "complete" == i.readyState || typeof i.readyState == a && (i.getElementsByTagName("body")[0] || i.body)) && A(), t || (typeof i.addEventListener != a && i.addEventListener("DOMContentLoaded", A, !1), y.ie && y.win && (i.attachEvent(g,
            function() {
                "complete" == i.readyState && (i.detachEvent(g, arguments.callee), A())
            }), h == top &&
            function() {
                if (!t) {
                    try {
                        i.documentElement.doScroll("left")
                    } catch(a) {
                        return setTimeout(arguments.callee, 0),
                        void 0
                    }
                    A()
                }
            } ()), y.wk &&
            function() {
                return t ? void 0 : /loaded|complete/.test(i.readyState) ? (A(), void 0) : (setTimeout(arguments.callee, 0), void 0)
            } (), D(A)))
        } (),
        function() {
            y.ie && y.win && window.attachEvent("onunload",
            function() {
                var b, c, d, e, f, a = o.length;
                for (b = 0; a > b; b++) o[b][0].detachEvent(o[b][1], o[b][2]);
                for (c = n.length, d = 0; c > d; d++) O(n[d]);
                for (e in y) y[e] = null;
                y = null;
                for (f in B) B[f] = null;
                B = null
            })
        } (),
        {
            registerObject: function(a, b, c, d) {
                if (y.w3 && a && b) {
                    var e = {};
                    e.id = a,
                    e.swfVersion = b,
                    e.expressInstall = c,
                    e.callbackFn = d,
                    m[m.length] = e,
                    V(a, !1)
                } else d && d({
                    success: !1,
                    id: a
                })
            },
            getObjectById: function(a) {
                return y.w3 ? H(a) : void 0
            },
            embedSWF: function(c, d, e, f, g, h, i, j, k, l) {
                var m = {
                    success: !1,
                    id: d
                };
                y.w3 && !(y.wk && y.wk < 312) && c && d && e && f && g ? (V(d, !1), C(function() {
                    var n, o, p, q, r, s;
                    if (e += "", f += "", n = {},
                    k && typeof k === b) for (o in k) n[o] = k[o];
                    if (n.data = c, n.width = e, n.height = f, p = {},
                    j && typeof j === b) for (q in j) p[q] = j[q];
                    if (i && typeof i === b) for (r in i) typeof p.flashvars != a ? p.flashvars += "&" + r + "=" + i[r] : p.flashvars = r + "=" + i[r];
                    if (T(g)) s = M(n, p, d),
                    n.id == d && V(d, !0),
                    m.success = !0,
                    m.ref = s;
                    else {
                        if (h && I()) return n.data = h,
                        J(n, p, d, l),
                        void 0;
                        V(d, !0)
                    }
                    l && l(m)
                })) : l && l(m)
            },
            switchOffAutoHideShow: function() {
                x = !1
            },
            ua: y,
            getFlashPlayerVersion: function() {
                return {
                    major: y.pv[0],
                    minor: y.pv[1],
                    release: y.pv[2]
                }
            },
            hasFlashPlayerVersion: T,
            createSWF: function(a, b, c) {
                return y.w3 ? M(a, b, c) : void 0
            },
            showExpressInstall: function(a, b, c, d) {
                y.w3 && I() && J(a, b, c, d)
            },
            removeSWF: function(a) {
                y.w3 && O(a)
            },
            createCSS: function(a, b, c, d) {
                y.w3 && U(a, b, c, d)
            },
            addDomLoadEvent: C,
            addLoadEvent: D,
            getQueryParamValue: function(a) {
                var c, d, b = i.location.search || i.location.hash;
                if (b) {
                    if (/\?/.test(b) && (b = b.split("?")[1]), null == a) return W(b);
                    for (c = b.split("&"), d = 0; d < c.length; d++) if (c[d].substring(0, c[d].indexOf("=")) == a) return W(c[d].substring(c[d].indexOf("=") + 1))
                }
                return ""
            },
            expressInstallCallback: function() {
                if (u) {
                    var a = Q(f);
                    a && p && (a.parentNode.replaceChild(p, a), q && (V(q, !0), y.ie && y.win && (p.style.display = "block")), r && r(s)),
                    u = !1
                }
            }
        }
    } ();
    try { !
        function(a) {
            "use strict";
            function k(a) {
                var b = new d;
                b.style.visibility = "hidden",
                b.style.position = "absolute",
                b.src = a
            }
            function l(a, b, c) {
                if (a.indexOf("&" + b + "=") > -1 || 0 === a.indexOf(b + "=")) {
                    var e, f, d = a.indexOf("&" + b + "=");
                    return - 1 === d && (d = a.indexOf(b + "=")),
                    e = a.indexOf("&", d + 1),
                    f = -1 !== e ? a.substr(0, d) + a.substr(e + (d ? 0 : 1)) + "&" + b + "=" + c: a.substr(0, d) + "&" + b + "=" + c
                }
                return a + "&" + b + "=" + c
            }
            function m() {
                return "indexedDB" in a ? !0 : (a.indexedDB = a.indexedDB || a.mozIndexedDB || a.webkitIndexedDB || a.msIndexedDB) ? !0 : !1
            }
            function o(a) {
                n = a;
                var c = b.getElementById("myswf");
                c && c.parentNode && c.parentNode.removeChild(c)
            }
            function u(h) {
                var j, o, q, r, u, v, w, x, y, z, A, B, D, F, C, E;
                h = h || {},
                j = {};
                for (o in s) q = h[o],
                j[o] = "undefined" != typeof q ? q: s[o];
                "function" == typeof j.domain && (j.domain = j.domain(a)),
                r = j.history,
                u = j.java,
                v = j.tests,
                w = j.baseurl,
                x = j.asseturi,
                y = j.phpuri,
                z = j.domain,
                A = this,
                this._ec = {},
                this.get = function(a, b, c) {
                    A._evercookie(a, b, void 0, void 0, c)
                },
                this.set = function(a, b) {
                    A._evercookie(a,
                    function() {},
                    b)
                },
                this._evercookie = function(c, d, e, f, g) {
                    if (void 0 === A._evercookie && (A = this), void 0 === f && (f = 0), 0 === f && (A.evercookie_database_storage(c, e), A.evercookie_indexdb_storage(c, e), j.authPath && A.evercookie_auth(c, e), u && A.evercookie_java(c, e), A._ec.userData = A.evercookie_userdata(c, e), A._ec.cookieData = A.evercookie_cookie(c, e), A._ec.localData = A.evercookie_local_storage(c, e), A._ec.globalData = A.evercookie_global_storage(c, e), A._ec.sessionData = A.evercookie_session_storage(c, e), A._ec.windowData = A.evercookie_window(c, e), r && (A._ec.historyData = A.evercookie_history(c, e))), void 0 !== e)("undefined" == typeof n || "undefined" == typeof p) && f++<v && setTimeout(function() {
                        A._evercookie(c, d, e, f, g)
                    },
                    300);
                    else if ((a.openDatabase && "undefined" == typeof A._ec.dbData || m() && ("undefined" == typeof A._ec.idbData || "" === A._ec.idbData) || "undefined" == typeof n || "undefined" == typeof A._ec.etagData || "undefined" == typeof A._ec.cacheData || "undefined" == typeof A._ec.javaData || b.createElement("canvas").getContext && ("undefined" == typeof A._ec.pngData || "" === A._ec.pngData) || "undefined" == typeof p) && f++<v) setTimeout(function() {
                        A._evercookie(c, d, e, f, g)
                    },
                    20);
                    else {
                        A._ec.lsoData = A.getFromStr(c, n),
                        n = void 0,
                        A._ec.slData = A.getFromStr(c, p),
                        p = void 0;
                        var l, o, h = A._ec,
                        i = [],
                        k = 0;
                        A._ec = {};
                        for (o in h) h[o] && "null" !== h[o] && "undefined" !== h[o] && (i[h[o]] = void 0 === i[h[o]] ? 1 : i[h[o]] + 1);
                        for (o in i) i[o] > k && (k = i[o], l = o);
                        void 0 === l || void 0 !== g && 1 === g || A.set(c, l),
                        "function" == typeof d && d(l, h)
                    }
                },
                this.evercookie_window = function(b, c) {
                    try {
                        if (void 0 === c) return this.getFromStr(b, a.name);
                        a.name = l(a.name, b, c)
                    } catch(d) {}
                },
                this.evercookie_userdata = function(a, b) {
                    try {
                        var c = this.createElem("div", "userdata_el", 1);
                        if (c.addBehavior) {
                            if (c.style.behavior = "url(#default#userData)", void 0 === b) return c.load(a),
                            c.getAttribute(a);
                            c.setAttribute(a, b),
                            c.save(a)
                        }
                    } catch(d) {}
                },
                this.evercookie_cache = function(a, c) {
                    if (void 0 !== c) b.cookie = j.cacheCookieName + "=" + c + "; path=/; domain=" + z,
                    A.ajax({
                        url: w + y + j.cachePath + "?name=" + a + "&cookie=" + j.cacheCookieName,
                        success: function() {}
                    });
                    else {
                        var d = this.getFromStr(j.cacheCookieName, b.cookie);
                        A._ec.cacheData = void 0,
                        b.cookie = j.cacheCookieName + "=; expires=Mon, 20 Sep 2010 00:00:00 UTC; path=/; domain=" + z,
                        A.ajax({
                            url: w + y + j.cachePath + "?name=" + a + "&cookie=" + j.cacheCookieName,
                            success: function(a) {
                                b.cookie = j.cacheCookieName + "=" + d + "; expires=Tue, 31 Dec 2030 00:00:00 UTC; path=/; domain=" + z,
                                A._ec.cacheData = a
                            }
                        })
                    }
                },
                this.evercookie_auth = function(a, b) {
                    void 0 !== b ? k("//" + b + "@" + location.host + w + y + j.authPath + "?name=" + a) : A.ajax({
                        url: w + y + j.authPath + "?name=" + a,
                        success: function(a) {
                            A._ec.authData = a
                        }
                    })
                },
                this.evercookie_etag = function(a, c) {
                    if (void 0 !== c) b.cookie = j.etagCookieName + "=" + c + "; path=/; domain=" + z,
                    A.ajax({
                        url: w + y + j.etagPath + "?name=" + a + "&cookie=" + j.etagCookieName,
                        success: function() {}
                    });
                    else {
                        var d = this.getFromStr(j.etagCookieName, b.cookie);
                        A._ec.etagData = void 0,
                        b.cookie = j.etagCookieName + "=; expires=Mon, 20 Sep 2010 00:00:00 UTC; path=/; domain=" + z,
                        A.ajax({
                            url: w + y + j.etagPath + "?name=" + a + "&cookie=" + j.etagCookieName,
                            success: function(a) {
                                b.cookie = j.etagCookieName + "=" + d + "; expires=Tue, 31 Dec 2030 00:00:00 UTC; path=/; domain=" + z,
                                A._ec.etagData = a
                            }
                        })
                    }
                },
                this.evercookie_java = function(a, c) {
                    function e(d) {
                        var e = b.getElementById(d);
                        void 0 !== c ? e.set(a, c) : A._ec.javaData = e.get(a)
                    }
                    var d = b.getElementById("ecAppletContainer");
                    "undefined" != typeof dtjava && (null !== d && void 0 !== d && d.length || (d = b.createElement("div"), d.setAttribute("id", "ecAppletContainer"), d.style.position = "absolute", d.style.top = "-3000px", d.style.left = "-3000px", d.style.width = "1px", d.style.height = "1px", b.body.appendChild(d)), "undefined" == typeof ecApplet ? dtjava.embed({
                        id: "ecApplet",
                        url: w + x + "/evercookie.jnlp",
                        width: "1px",
                        height: "1px",
                        placeholder: "ecAppletContainer"
                    },
                    {},
                    {
                        onJavascriptReady: e
                    }) : e("ecApplet"))
                },
                this.evercookie_lso = function(a, c) {
                    var d = b.getElementById("swfcontainer"),
                    e = {},
                    g = {},
                    h = {};
                    null !== d && void 0 !== d && d.length || (d = b.createElement("div"), d.setAttribute("id", "swfcontainer"), b.body.appendChild(d)),
                    void 0 !== c && (e.everdata = a + "=" + c),
                    g.swliveconnect = "true",
                    h.id = "myswf",
                    h.name = "myswf",
                    f.embedSWF(w + x + "/evercookie.swf", "swfcontainer", "1", "1", "9.0.0", !1, e, g, h)
                },
                this.evercookie_png = function(a, c) {
                    var f, g, h, e = b.createElement("canvas");
                    e.style.visibility = "hidden",
                    e.style.position = "absolute",
                    e.width = 200,
                    e.height = 1,
                    e && e.getContext && (f = new d, f.style.visibility = "hidden", f.style.position = "absolute", void 0 !== c ? b.cookie = j.pngCookieName + "=" + c + "; path=/; domain=" + z: (A._ec.pngData = void 0, g = e.getContext("2d"), h = this.getFromStr(j.pngCookieName, b.cookie), b.cookie = j.pngCookieName + "=; expires=Mon, 20 Sep 2010 00:00:00 UTC; path=/; domain=" + z, f.onload = function() {
                        b.cookie = j.pngCookieName + "=" + h + "; expires=Tue, 31 Dec 2030 00:00:00 UTC; path=/; domain=" + z,
                        A._ec.pngData = "",
                        g.drawImage(f, 0, 0);
                        var d, e, a = g.getImageData(0, 0, 200, 1),
                        c = a.data;
                        for (d = 0, e = c.length; e > d && 0 !== c[d] && (A._ec.pngData += String.fromCharCode(c[d]), 0 !== c[d + 1]) && (A._ec.pngData += String.fromCharCode(c[d + 1]), 0 !== c[d + 2]); d += 4) A._ec.pngData += String.fromCharCode(c[d + 2])
                    }), f.src = w + y + j.pngPath + "?name=" + a + "&cookie=" + j.pngCookieName)
                },
                this.evercookie_local_storage = function(a, b) {
                    try {
                        if (g) {
                            if (void 0 === b) return g.getItem(a);
                            g.setItem(a, b)
                        }
                    } catch(c) {}
                },
                this.evercookie_database_storage = function(b, c) {
                    try {
                        if (a.openDatabase) {
                            var d = a.openDatabase("sqlite_evercookie", "", "evercookie", 1048576);
                            void 0 !== c ? d.transaction(function(a) {
                                a.executeSql("CREATE TABLE IF NOT EXISTS cache(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, value TEXT NOT NULL, UNIQUE (name))", [],
                                function() {},
                                function() {}),
                                a.executeSql("INSERT OR REPLACE INTO cache(name, value) VALUES(?, ?)", [b, c],
                                function() {},
                                function() {})
                            }) : d.transaction(function(a) {
                                a.executeSql("SELECT value FROM cache WHERE name=?", [b],
                                function(a, b) {
                                    A._ec.dbData = b.rows.length >= 1 ? b.rows.item(0).value: ""
                                },
                                function() {})
                            })
                        }
                    } catch(e) {}
                },
                this.evercookie_indexdb_storage = function(b, c) {
                    var d, e;
                    try {
                        "indexedDB" in a || (indexedDB = a.indexedDB || a.mozIndexedDB || a.webkitIndexedDB || a.msIndexedDB, IDBTransaction = a.IDBTransaction || a.webkitIDBTransaction || a.msIDBTransaction, IDBKeyRange = a.IDBKeyRange || a.webkitIDBKeyRange || a.msIDBKeyRange),
                        indexedDB && (d = 1, e = indexedDB.open("idb_evercookie", d), e.onerror = function() {},
                        e.onupgradeneeded = function(a) {
                            var b = a.target.result;
                            b.createObjectStore("evercookie", {
                                keyPath: "name",
                                unique: !1
                            })
                        },
                        e.onsuccess = void 0 !== c ?
                        function(a) {
                            var e, f, d = a.target.result;
                            d.objectStoreNames.contains("evercookie") && (e = d.transaction(["evercookie"], "readwrite"), f = e.objectStore("evercookie"), f.put({
                                name: b,
                                value: c
                            })),
                            d.close()
                        }: function(a) {
                            var d, e, f, c = a.target.result;
                            c.objectStoreNames.contains("evercookie") ? (d = c.transaction(["evercookie"]), e = d.objectStore("evercookie"), f = e.get(b), f.onsuccess = function() {
                                A._ec.idbData = void 0 === f.result ? void 0 : f.result.value
                            }) : A._ec.idbData = void 0,
                            c.close()
                        })
                    } catch(f) {}
                },
                this.evercookie_session_storage = function(a, b) {
                    try {
                        if (i) {
                            if (void 0 === b) return i.getItem(a);
                            i.setItem(a, b)
                        }
                    } catch(c) {}
                },
                this.evercookie_global_storage = function(a, b) {
                    if (e) {
                        var c = this.getHost();
                        try {
                            if (void 0 === b) return e[c][a];
                            e[c][a] = b
                        } catch(d) {}
                    }
                },
                this.encode = function(a) {
                    var c, d, e, f, g, h, i, b = "",
                    j = 0;
                    for (a = this._utf8_encode(a); j < a.length;) c = a.charCodeAt(j++),
                    d = a.charCodeAt(j++),
                    e = a.charCodeAt(j++),
                    f = c >> 2,
                    g = (3 & c) << 4 | d >> 4,
                    h = (15 & d) << 2 | e >> 6,
                    i = 63 & e,
                    isNaN(d) ? h = i = 64 : isNaN(e) && (i = 64),
                    b = b + t.charAt(f) + t.charAt(g) + t.charAt(h) + t.charAt(i);
                    return b
                },
                this.decode = function(a) {
                    var c, d, e, f, g, h, i, b = "",
                    j = 0;
                    for (a = a.replace(/[^A-Za-z0-9\+\/\=]/g, ""); j < a.length;) f = t.indexOf(a.charAt(j++)),
                    g = t.indexOf(a.charAt(j++)),
                    h = t.indexOf(a.charAt(j++)),
                    i = t.indexOf(a.charAt(j++)),
                    c = f << 2 | g >> 4,
                    d = (15 & g) << 4 | h >> 2,
                    e = (3 & h) << 6 | i,
                    b += String.fromCharCode(c),
                    64 !== h && (b += String.fromCharCode(d)),
                    64 !== i && (b += String.fromCharCode(e));
                    return b = this._utf8_decode(b)
                },
                this._utf8_encode = function(a) {
                    a = a.replace(/\r\n/g, "\n");
                    for (var e, b = "",
                    c = 0,
                    d = a.length; d > c; c++) e = a.charCodeAt(c),
                    128 > e ? b += String.fromCharCode(e) : e > 127 && 2048 > e ? (b += String.fromCharCode(192 | e >> 6), b += String.fromCharCode(128 | 63 & e)) : (b += String.fromCharCode(224 | e >> 12), b += String.fromCharCode(128 | 63 & e >> 6), b += String.fromCharCode(128 | 63 & e));
                    return b
                },
                this._utf8_decode = function(a) {
                    for (var b = "",
                    c = 0,
                    d = a.length,
                    e = 0,
                    g = 0,
                    h = 0; d > c;) e = a.charCodeAt(c),
                    128 > e ? (b += String.fromCharCode(e), c += 1) : e > 191 && 224 > e ? (g = a.charCodeAt(c + 1), b += String.fromCharCode((31 & e) << 6 | 63 & g), c += 2) : (g = a.charCodeAt(c + 1), h = a.charCodeAt(c + 2), b += String.fromCharCode((15 & e) << 12 | (63 & g) << 6 | 63 & h), c += 3);
                    return b
                },
                this.evercookie_history = function(a, b) {
                    var e, f, c = (t + "-").split(""),
                    d = "http://www.bsfit.com.cn/evercookie/cache/" + this.getHost() + "/" + a,
                    g = "",
                    h = "",
                    i = 1;
                    if (void 0 !== b) {
                        if (this.hasVisited(d)) return;
                        for (this.createIframe(d, "if"), d += "/", f = this.encode(b).split(""), e = 0; e < f.length; e++) d += f[e],
                        this.createIframe(d, "if" + e);
                        d += "-",
                        this.createIframe(d, "if_")
                    } else if (this.hasVisited(d)) {
                        for (d += "/";
                        "-" !== g && 1 === i;) for (i = 0, e = 0; e < c.length; e++) if (this.hasVisited(d + c[e])) {
                            g = c[e],
                            "-" !== g && (h += g),
                            d += g,
                            i = 1;
                            break
                        }
                        return this.decode(h)
                    }
                },
                this.createElem = function(a, c, d) {
                    var e;
                    return e = void 0 !== c && b.getElementById(c) ? b.getElementById(c) : b.createElement(a),
                    e.style.visibility = "hidden",
                    e.style.position = "absolute",
                    c && e.setAttribute("id", c),
                    d && b.body.appendChild(e),
                    e
                },
                this.createIframe = function(a, b) {
                    var c = this.createElem("iframe", b, 1);
                    return c.setAttribute("src", a),
                    c
                },
                B = this.waitForSwf = function(a) {
                    void 0 === a ? a = 0 : a++,
                    v > a && "undefined" == typeof f && setTimeout(function() {
                        B(a)
                    },
                    300)
                },
                this.evercookie_cookie = function(a, c) {
                    return void 0 === c ? this.getFromStr(a, b.cookie) : (b.cookie = a + "=; expires=Mon, 20 Sep 2010 00:00:00 UTC; path=/; domain=" + z, b.cookie = a + "=" + c + "; expires=Tue, 31 Dec 2030 00:00:00 UTC; path=/; domain=" + z, void 0)
                },
                this.getFromStr = function(a, b) {
                    if ("string" == typeof b) {
                        var e, f, c = a + "=",
                        d = b.split(/[;&]/);
                        for (e = 0; e < d.length; e++) {
                            for (f = d[e];
                            " " === f.charAt(0);) f = f.substring(1, f.length);
                            if (0 === f.indexOf(c)) return f.substring(c.length, f.length)
                        }
                    }
                },
                this.getHost = function() {
                    return c(a.location.host.split(":")[0])
                },
                this.toHex = function(a) {
                    for (var e, b = "",
                    c = a.length,
                    d = 0; c > d;) {
                        for (e = a.charCodeAt(d++).toString(16); e.length < 2;) e = "0" + e;
                        b += e
                    }
                    return b
                },
                this.fromHex = function(a) {
                    for (var d, b = "",
                    c = a.length; c >= 0;) d = c - 2,
                    b = String.fromCharCode("0x" + a.substring(d, c)) + b,
                    c = d;
                    return b
                },
                this.hasVisited = function(a) {
                    if ( - 1 === this.no_color) {
                        var b = this._getRGB("http://bsfit-was-here-this-should-never-be-visited.com", -1); - 1 === b && (this.no_color = this._getRGB("http://bsfit-was-here-" + Math.floor(9999999 * Math.random()) + "rand.com"))
                    }
                    return 0 === a.indexOf("https:") || 0 === a.indexOf("http:") ? this._testURL(a, this.no_color) : this._testURL("http://" + a, this.no_color) || this._testURL("https://" + a, this.no_color) || this._testURL("http://www." + a, this.no_color) || this._testURL("https://www." + a, this.no_color)
                },
                C = this.createElem("a", "_ec_rgb_link"),
                E = "#_ec_rgb_link:visited{display:none;color:#FF0000}";
                try {
                    D = 1,
                    F = b.createElement("style"),
                    F.styleSheet ? F.styleSheet.innerHTML = E: F.innerHTML ? F.innerHTML = E: F.appendChild(b.createTextNode(E))
                } catch(G) {
                    D = 0
                }
                this._getRGB = function(a, c) {
                    if (c && 0 === D) return - 1;
                    C.href = a,
                    C.innerHTML = a,
                    b.body.appendChild(F),
                    b.body.appendChild(C);
                    var d;
                    if (b.defaultView) {
                        if (null == b.defaultView.getComputedStyle(C, null)) return - 1;
                        d = b.defaultView.getComputedStyle(C, null).getPropertyValue("color")
                    } else d = C.currentStyle.color;
                    return d
                },
                this._testURL = function(a, b) {
                    var c = this._getRGB(a);
                    return "rgb(255, 0, 0)" === c || "#ff0000" === c ? 1 : b && c !== b ? 1 : 0
                }
            }
            var g, i, n, p, s, t, b = a.document,
            d = a.Image,
            e = a.globalStorage,
            f = a.swfobject;
            try {
                g = a.localStorage
            } catch(h) {}
            try {
                i = a.sessionStorage
            } catch(j) {}
            s = {
                history: !1,
                java: !1,
                tests: 10,
                silverlight: !1,
                domain: c(a.location.host.split(":")[0]),
                baseurl: "",
                asseturi: "/assets",
                phpuri: "/php",
                authPath: !1,
                pngCookieName: "evercookie_png",
                pngPath: "/evercookie_png.php",
                etagCookieName: "evercookie_etag",
                etagPath: "/evercookie_etag.php",
                cacheCookieName: "evercookie_cache",
                cachePath: "/evercookie_cache.php"
            },
            t = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
            a._evercookie_flash_var = o,
            a.evercookie = a.Evercookie = u
        } (window)
    } catch(C) {}
    M = "BSFIT_DEVICEID",
    Q = "BSFIT_OkLJUJ",
    R.prototype = {
        constructor: R(),
        checkBroswer: function() {
            function b() {
                var b = navigator.userAgent.toString(),
                c = "MSIE",
                d = b.indexOf(c);
                return a = d >= 0 ? "IE": "other"
            }
            var a = b()
        },
        checkOperaBroswer: function() {
            return window.opera
        },
        getCanvansCode: function(a) {
            return new K("canvasCode", a)
        },
        getCookieCode: function() {
            return new K("cookieCode", D(Q))
        },
        getUserAgent: function() {
            return new K("userAgent", navigator.userAgent.toString())
        },
        getScrHeight: function() {
            return new K("scrHeight", window.screen.height.toString())
        },
        getScrWidth: function() {
            return new K("scrWidth", window.screen.width.toString())
        },
        getScrAvailHeight: function() {
            return new K("scrAvailHeight", window.screen.availHeight.toString())
        },
        getScrAvailWidth: function() {
            return new K("scrAvailWidth", window.screen.availWidth.toString())
        },
        md5ScrColorDepth: function() {
            return new K("scrColorDepth", window.screen.colorDepth.toString())
        },
        getScrDeviceXDPI: function() {
            var a = "";
            return a = "IE" == this.checkBroswer() ? window.screen.deviceXDPI.toString() : "",
            new K("scrDeviceXDPI", a)
        },
        getAppCodeName: function() {
            return new K("appCodeName", navigator.appCodeName.toString())
        },
        getAppName: function() {
            return new K("appName", navigator.appName.toString())
        },
        getAppVersion: function() {
            return new K("appVersion", navigator.appVersion.toString())
        },
        getJavaEnabled: function() {
            return new K("javaEnabled", navigator.javaEnabled().toString())
        },
        getMimeTypes: function() {
            var c, a = navigator.mimeTypes,
            b = "";
            for (c = 0; c < a.length; c++) b += a[c].type + "#";
            return new K("mimeTypes", b.substr(0, b.length - 1))
        },
        getPlatform: function() {
            return new K("platform", navigator.platform.length.toString())
        },
        getAppMinorVersion: function() {
            var a = "";
            return a = "IE" == this.checkBroswer() ? navigator.appMinorVersion.toString() : "",
            new K("appMinorVersion", a)
        },
        getBrowserLanguage: function() {
            var a = "";
            return a = "IE" == this.checkBroswer() || this.checkOperaBroswer() ? navigator.browserLanguage.toString() : this.getLanguage(),
            new K("browserLanguage", a)
        },
        getLanguage: function() {
            return null != navigator.language ? navigator.language.toString() : ""
        },
        getCookieEnabled: function() {
            return new K("cookieEnabled", navigator.cookieEnabled.toString())
        },
        getCpuClass: function() {
            var a;
            return a = "IE" == this.checkBroswer() ? navigator.cpuClass.toString() : "",
            new K("cpuClass", a)
        },
        getOnLine: function() {
            return new K("onLine", navigator.onLine.toString())
        },
        getSystemLanguage: function() {
            var a = "";
            return a = "IE" == this.checkBroswer() || this.checkOperaBroswer() ? navigator.systemLanguage.toString() : "",
            new K("systemLanguage", a)
        },
        getUserLanguage: function() {
            var a = "";
            return a = "IE" == this.checkBroswer() || this.checkOperaBroswer() ? navigator.userLanguage.toString() : "",
            new K("userLanguage", a)
        },
        getTimeZone: function() {
            var a = new Date,
            b = a.getTimezoneOffset() / 60;
            return new K("timeZone", b)
        },
        getPlugins: function(a) {
            var b, c;
            if ("IE" == this.checkBroswer()) return new K("plugins", a.replace(new RegExp(",", "gm"), "#"));
            for (b = navigator.plugins, c = "", i = 0; i < b.length; i++) c += b[i].name.toString() + "#";
            return new K("plugins", c)
        },
        getFlashVersion: function() {
            var c, a = 0,
            b = 0;
            return "IE" == this.checkBroswer() ? (c = new ActiveXObject("ShockwaveFlash.ShockwaveFlash"), c && (a = 1, b = Number(c.GetVariable("$version").split(" ")[1].replace(/,/g, ".").replace(/^(d+.d+).*$/, "$1")))) : navigator.plugins && navigator.plugins.length > 0 && (c = navigator.plugins["Shockwave Flash"], c && (a = 1, flashArr = c.description.split(" "), b = flashArr[2] + " " + flashArr[3])),
            new K("flashVersion", b)
        },
        getHistoryList: function() {
            return new K("historyList", window.history.length)
        },
        getPartnerCode: function() {
            return new K("partnerCode", partnerCode)
        },
        getVersion: function() {
            return new K("sdkVersion", version)
        },
        getWebGLCode: function() {
            var a = this.cfp.getWebglFp();
            return new K("webGLCode", a)
        },
        getSessionStorage: function(a) {
            return new K("sessionStorage", a)
        },
        getLocalStorage: function(a) {
            return new K("localStorage", a)
        },
        getIndexedDb: function(a) {
            return new K("indexedDb", a)
        },
        getOpenDatabase: function(a) {
            return new K("openDatabase", a)
        },
        getDoNotTrack: function(a) {
            return new K("doNotTrack", a)
        },
        getAdblock: function(a) {
            return new K("adblock", a)
        },
        getHasLiedLanguages: function(a) {
            return new K("hasLiedLanguages", a)
        },
        getHasLiedResolution: function(a) {
            return new K("hasLiedResolution", a)
        },
        getHasLiedOs: function(a) {
            return new K("hasLiedOs", a)
        },
        getHasLiedBrowser: function(a) {
            return new K("hasLiedBrowser", a)
        },
        getTouchSupport: function(a) {
            return new K("touchSupport", a.replace(new RegExp(",", "gm"), "#"))
        },
        getJsFonts: function(a) {
            return new K("jsFonts", a.replace(new RegExp(",", "gm"), "#"))
        },
        getDfpMoreInfo: function() {
            var a = this;
            a.cfp.get(function(b, c) {
                var d, e;
                a.moreInfoArray.push(a.getCanvansCode(b + ""));
                for (d in c) switch (b = c[d].key, e = c[d].value + "", b) {
                case "session_storage":
                    a.moreInfoArray.push(a.getSessionStorage(e));
                    break;
                case "local_storage":
                    a.moreInfoArray.push(a.getLocalStorage(e));
                    break;
                case "indexed_db":
                    a.moreInfoArray.push(a.getIndexedDb(e));
                    break;
                case "open_database":
                    a.moreInfoArray.push(a.getOpenDatabase(e));
                    break;
                case "do_not_track":
                    a.moreInfoArray.push(a.getDoNotTrack(e));
                    break;
                case "ie_plugins":
                    a.moreInfoArray.push(a.getPlugins(e));
                    break;
                case "regular_plugins":
                    a.moreInfoArray.push(a.getPlugins());
                    break;
                case "adblock":
                    a.moreInfoArray.push(a.getAdblock(e));
                    break;
                case "has_lied_languages":
                    a.moreInfoArray.push(a.getHasLiedLanguages(e));
                    break;
                case "has_lied_resolution":
                    a.moreInfoArray.push(a.getHasLiedResolution(e));
                    break;
                case "has_lied_os":
                    a.moreInfoArray.push(a.getHasLiedOs(e));
                    break;
                case "has_lied_browser":
                    a.moreInfoArray.push(a.getHasLiedBrowser(e));
                    break;
                case "touch_support":
                    a.moreInfoArray.push(a.getTouchSupport(e));
                    break;
                case "js_fonts":
                    a.moreInfoArray.push(a.getJsFonts(e))
                }
            })
        },
        getMachineCode: function() {
            return [this.getCookieCode(), this.getUserAgent(), this.getScrHeight(), this.getScrWidth(), this.getScrAvailHeight(), this.getScrAvailWidth(), this.md5ScrColorDepth(), this.getScrDeviceXDPI(), this.getAppCodeName(), this.getAppName(), this.getAppVersion(), this.getJavaEnabled(), this.getMimeTypes(), this.getPlatform(), this.getAppMinorVersion(), this.getBrowserLanguage(), this.getCookieEnabled(), this.getCpuClass(), this.getOnLine(), this.getSystemLanguage(), this.getUserLanguage(), this.getTimeZone(), this.getFlashVersion(), this.getHistoryList(), this.getPartnerCode(), this.getVersion()]
        },
        getpackStr: function() {
            function e(a) {
                return function(b, c) {
                    var d, e;
                    if ("object" == typeof b && "object" == typeof c && b && c) return d = b[a],
                    e = c[a],
                    d === e ? 0 : typeof d == typeof e ? e > d ? -1 : 1 : typeof e > typeof d ? -1 : 1;
                    throw "error"
                }
            }
            var g, h, b = this.getMachineCode(),
            c = b.concat(this.moreInfoArray),
            d = c.sort(e("key")),
            f = "";
            for (g = 0; g < d.length; g++) f += encodeURIComponent(d[g].key) + "=" + encodeURIComponent(d[g].value).toUpperCase() + "&";
            return f = f.substr(0, f.length - 1),
            h = a.SHA256(f).toString().toLowerCase(),
            f = f + "&sign=" + h
        },
        getSignature: function() {
            var b = this.getpackStr(),
            c = "packageStr=" + encodeURIComponent(b) + "&partnerCode=" + encodeURIComponent(partnerCode) + "&platform=" + encodeURIComponent(platform);
            return a.HmacSHA1(c, secretKey).toString()
        },
        initEc: function() {
            var a = this;
            this.ec.get(Q,
            function(b) { (b = void 0 || null == b) && a.ec.set(Q, I())
            }),
            this.getDfpMoreInfo()
        },
        getFingerPrint: function() {
            var a = this;
            a.initEc(),
            (new Date).getTime() - D("BSFIT_EXPIRATION") < 0 && null != D(M) && void 0 != D(M) || setTimeout(function() {
                function c() {
                    var c, d;
                    if (4 == b.readyState) if (200 == b.status) {
                        c = JSON.parse(b.responseText);
                        for (d in c) console.log(d + " " + c[d]),
                        "deviceId" == d ? (D(M) != c[d] && E(M, c[d]), a.deviceEc.set(M, c[d])) : "expiration" == d ? E("BSFIT_EXPIRATION", c[d]) : "traceId" == d && E("BSFIT_TRACEID", c[d])
                    } else 401 == b.status
                }
                var b = new XMLHttpRequest;
                b.onreadystatechange = c,
                b.open("POST", dfpURL, !0),
                b.setRequestHeader("Content-Type", "application/json;charset=UTF-8"),
                b.send(JSON.stringify({
                    partnerCode: partnerCode,
                    packageStr: a.getpackStr(),
                    platform: platform,
                    signature: a.getSignature()
                }))
            },
            500)
        }
    },
    setTimeout(S(), 1)
} ();
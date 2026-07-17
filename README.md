#### [EPG Viewer](https://github.com/warren-bank/Android-IPTV-XMLTV-EPG-Viewer)

Android app that reads an IPTV XMLTV file, and displays an EPG (electronic program guide).

#### Features:

* IPTV XMLTV file can be read from either a network URL or the local filesystem
* list of channels can be filtered

#### Intent filters:

To enable the automatic updating of EPG data from an externally bookmarked URL,<br>
which returns a server response containing a valid IPTV XMLTV file.

Supported Intents:

1. action = `android.intent.action.VIEW`
   * (optional) package = `com.github.warren_bank.epg_viewer`
   * (optional) class   = `com.github.warren_bank.epg_viewer.EpgActivity`
   * data = `<any URL>`
   * type = any of:
     ```json
     [
       "application/xml",
       "application/xmltv",
       "text/xml",
       "text/xmltv"
     ]
     ```
2. action = `android.intent.action.VIEW`
   * (optional) package = `com.github.warren_bank.epg_viewer`
   * (optional) class   = `com.github.warren_bank.epg_viewer.EpgActivity`
   * data = `<URL that ends with a .xml, .xmltv, .XML, or .XMLTV file extension>`

#### Legal:

* copyright: [Warren Bank](https://github.com/warren-bank)
* license: [GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt)

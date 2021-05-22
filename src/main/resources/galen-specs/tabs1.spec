@objects
    tabs        xpath     //div[contains(@class, 'header__nav-links')]

= Main section =
    @on chrome
        tabs:
            image file  layout-2-tabs-actual.png, error 10px
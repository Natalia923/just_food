@objects
    tabs        xpath     //div[contains(@class, 'header__nav-links')]

= Main section =
    @on chrome
        tabs:
            image file  tabs.png, error 10px
# Almosafer_Selenium

1. Navigate to URL https://www.almosafer.cpm/en
2. Assertions:
    - Default language in English.
    - Default currency is SAR
    - Contact numbers are correct
    - "qitaf" logo is displayed in footer
    - "Hotels" tab is NOT selected by default
    - Flight departure date is today + 1 day
    - Flight return date is today + 2 days
  
3. Change the language randomly and make assertions that language is updated correctly.
4. Switch to "Hotels" tab:
   - If current language is English, then randomly type from (Dubai, Riyadh, Jeddah)
   - If current languahe is Arabic, then randomly type from (دبي, رياض, جدة)
   - Click on the first result from the autocomplete list
5. Randomly select (1 room, 2 adults, 0 children) and search
6. Wait for loading to complete and make some assertions
7. Sort results (Lowest price) and make assertion if results are sorted low to high

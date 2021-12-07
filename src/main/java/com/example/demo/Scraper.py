import requests
import re
from bs4 import BeautifulSoup

class Fish:
    def __init__(self, name, country, locality, link):
        self.name = name
        self.country = country
        self.location = locality
        self.link = link

fishList = []

# Job Index
def fishScrab():
    URL = 'https://www.fishbase.us/FishWatcher/CollectionsList.php?what=all&sortby=species&allrec=on'
    headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}

    fish_elems = []

    page = requests.get(URL, headers=headers)

    soup = BeautifulSoup(page.content, 'html.parser')
    results = soup.find('table')
    fish_elems = soup.find_all('tr');
    fish_elems.pop(0); #Removing the first useless element
    #print(fish_elems[0].find_all('td')[0])


    for fish_elem in fish_elems:
        allTds = fish_elem.find_all('td')
        fishName = allTds[0].find('i').getText();
        aTag = allTds[0].find('a', href=True);
        fishLink = aTag['href'];
        country = allTds[1].getText();
        local = allTds[2].getText();
        newFish = Fish(fishName, country, local, fishLink);
        fishList.append(newFish);



fishScrab()

def perFishScrab(fish):
    URL = 'https://www.fishbase.us/FishWatcher/' + fish.link;
    headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}

    page = requests.get(URL, headers=headers)

    soup = BeautifulSoup(page.content, 'html.parser')
    results = soup.find_all('table')[1];
    tr_elems = results.find_all('tr');


    for tr_elem in tr_elems:
        fish.habitatType = tr_elems[5].find_all('td')[1].getText();
        fish.waterType = tr_elems[6].find_all('td')[1].getText();
        fish.waterDepth = tr_elems[7].find_all('td')[1].getText();
        fish.abundance = tr_elems[18].find_all('td')[1].getText();
        latArray = re.findall("([0-9]*[.][0-9]*)",tr_elems[8].find_all('td')[1].getText());
        longArray = re.findall("([0-9]*[.][0-9]*)",tr_elems[9].find_all('td')[1].getText());
        if len(latArray) > 0:
            fish.lat = latArray
            fish.long = longArray


for fish in fishList:
    perFishScrab(fish);

for fish in fishList:
    if hasattr(fish, 'lat'):
        print("Latitude: " + fish.lat[len(fish.lat) - 1] + " Longitude: " + fish.long[len(fish.long) - 1]);
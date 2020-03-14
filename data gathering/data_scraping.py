from YALogger.custom_logger import Logger
import Constants
import urllib3
from bs4 import BeautifulSoup
from FileUtil.FilePickling import save_obj

# initailize the YALogger
Logger.initialize_logger(
    logger_prop_file_path=".\logger.properties", log_file_path="./logs"
)

def _create_main_parser(html_data):
    Logger.log(
        "info",
        "data_scraping",
        "_create_main_parser",
        "Scraping global details from page - Coronavirus cases, Deaths, Recovered"
    )
    soup = BeautifulSoup(html_data, "html.parser")
    return soup

def _parse_total_counter_info(html_data, corona_data):
    soup = _create_main_parser(html_data)
    main_counter_wrap_divs = soup.findAll("div", class_="maincounter-number")
    global_tags = ['total_corona_virus_cases', 'total_deaths','total_recovered']
    for global_counter_div, tag in zip(main_counter_wrap_divs, global_tags):
        global_counter_data = global_counter_div.find("span").find(text=True).encode('utf-8').decode('utf8').strip().replace(',','')
        global_counter_data_int = int(global_counter_data)
        Logger.log(
            "info",
            "data_scraping",
            "_parse_total_counter_info",
            global_counter_data
        )
        corona_data[tag] = global_counter_data_int
    return corona_data

def scrape_corona_virus_data():
    http_pool = urllib3.connection_from_url(Constants.SOURCE_ROOT_WEBSITE_URL)
    html_data = http_pool.request("GET", Constants.SOURCE_ROOT_WEBSITE_URL).data.decode("utf8")
    corona_data = {}
    corona_data = _parse_total_counter_info(html_data, corona_data)
    save_obj(corona_data, "Corona_Virus_Data", "Data", True)
    #Logger.log('DEBUG', 'data_scraping', 'scrape_corona_virus_data', html_data)

if __name__ == "__main__":
    Logger.perform_method_entry_logging('data_scraping', 'scrape_corona_virus_data')
    scrape_corona_virus_data()
    Logger.perform_method_exit_logging('data_scraping', 'scrape_corona_virus_data')

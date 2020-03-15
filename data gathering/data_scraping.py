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
        "creating bs4 parser"
    )
    soup = BeautifulSoup(html_data, "html.parser")
    return soup

def _parse_total_counter_info(html_data, corona_data):
    Logger.perform_method_entry_logging('data_scraping', '_parse_total_counter_info')

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
    
    Logger.perform_method_exit_logging('data_scraping', '_parse_total_counter_info')
    return corona_data

def _parse_country_info(html_data, corona_data):
    Logger.perform_method_entry_logging('data_scraping', '_parse_country_info')
    
    soup = _create_main_parser(html_data)
    country_table_tag = soup.find("table", attrs={'id' : 'main_table_countries'})
    country_rows_tag = country_table_tag.findAll("tr")
    for country_row_tag in country_rows_tag[1 : len(country_rows_tag)-1]: #dont consider header and last row
        country_data = country_row_tag.findAll("td")
        country_name_tag = country_data[0]
        country_name = country_name_tag.find(text=True).encode("utf-8").strip()
        if country_name == b'':
            if country_name_tag.find("a", class_="mt_a") != None:
                country_name = country_name_tag.find("a", class_="mt_a").find(text=True).encode("utf-8").strip()
            else:
                country_name = country_name_tag.find("span").find(text=True).encode("utf-8").strip()
        country_name = country_name.decode('utf8')
        corona_data[country_name] = {}
        corona_data[country_name]['total_cases'] = country_data[1].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','')
        corona_data[country_name]['new_cases'] = country_data[2].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','')
        corona_data[country_name]['total_deaths'] = country_data[3].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','') 
        corona_data[country_name]['new_deaths'] = country_data[4].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','') 
        corona_data[country_name]['total_recovered'] = country_data[5].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','') 
        corona_data[country_name]['active_cases'] = country_data[6].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','')
        corona_data[country_name]['critical'] = country_data[7].find(text=True).encode("utf-8").strip().decode('utf8').replace(',','') 

    Logger.perform_method_exit_logging('data_scraping', '_parse_country_info')
    return corona_data

def scrape_corona_virus_data():
    Logger.perform_method_entry_logging('data_scraping', '_parse_country_info')

    http_pool = urllib3.connection_from_url(Constants.SOURCE_ROOT_WEBSITE_URL)
    html_data = http_pool.request("GET", Constants.SOURCE_ROOT_WEBSITE_URL).data.decode("utf8")
    corona_data = {}
    corona_data = _parse_total_counter_info(html_data, corona_data)
    corona_data = _parse_country_info(html_data, corona_data)
    save_obj(corona_data, "Corona_Virus_Data", "Data", True)
    #Logger.log('DEBUG', 'data_scraping', 'scrape_corona_virus_data', html_data)

    Logger.perform_method_exit_logging('data_scraping', 'scrape_corona_virus_data')

if __name__ == "__main__":
    Logger.perform_method_entry_logging('data_scraping', 'scrape_corona_virus_data')
    scrape_corona_virus_data()
    Logger.perform_method_exit_logging('data_scraping', 'scrape_corona_virus_data')

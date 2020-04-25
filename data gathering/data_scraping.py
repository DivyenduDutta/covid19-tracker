""" pylint missing-module-docstring-disable """
from datetime import datetime
#from YALogger.custom_logger import Logger
from bs4 import BeautifulSoup
import urllib3
import Constants
from FileUtil.FilePickling import save_obj


# initailize the YALogger
#Logger.initialize_logger(
#    logger_prop_file_path="./logger.properties", log_file_path="./logs"
#)


def _create_main_parser(html_data):
    #Logger.log("info", "data_scraping", "_create_main_parser", "creating bs4 parser")
    soup = BeautifulSoup(html_data, "html.parser")
    return soup


def _parse_total_counter_info(html_data, corona_data):
    #Logger.perform_method_entry_logging("data_scraping", "_parse_total_counter_info")

    soup = _create_main_parser(html_data)
    main_counter_wrap_divs = soup.findAll("div", class_="maincounter-number")
    global_tags = ["total_corona_virus_cases", "total_deaths", "total_recovered"]
    for global_counter_div, tag in zip(main_counter_wrap_divs, global_tags):
        global_counter_data = (
            global_counter_div.find("span")
            .find(text=True)
            .encode("utf-8")
            .decode("utf8")
            .strip()
            .replace(",", "")
        )
        global_counter_data_int = int(global_counter_data)
        #Logger.log(
        #    "info", "data_scraping", "_parse_total_counter_info", global_counter_data
        #)
        corona_data[tag] = global_counter_data_int

    corona_data["data_received_date"] = datetime.now().strftime("%Y-%m-%d")
    #Logger.perform_method_exit_logging("data_scraping", "_parse_total_counter_info")
    return corona_data


def _parse_country_info(html_data, corona_data):
    #Logger.perform_method_entry_logging("data_scraping", "_parse_country_info")

    soup = _create_main_parser(html_data)
    country_table_tag = soup.find("table", attrs={"id": "main_table_countries_today"})
    country_rows_tag = country_table_tag.findAll("tr")
    for country_row_tag in country_rows_tag[
        1 : len(country_rows_tag) - 1
    ]:  # dont consider header and last row
        country_data = country_row_tag.findAll("td")
        country_name_tag = country_data[0]
        country_name = country_name_tag.find(text=True).encode("utf-8").strip()
        #print(country_name_tag)
        if country_name == b"":
            if country_name_tag.find("a", class_="mt_a") is not None:
                country_name = (
                    country_name_tag.find("a", class_="mt_a")
                    .find(text=True)
                    .encode("utf-8")
                    .strip()
                )
            elif country_name_tag.find("span") is not None:
                country_name = (
                    country_name_tag.find("span")
                    .find(text=True)
                    .encode("utf-8")
                    .strip()
                )
            else:
                continue
        country_data_obj = {}
        country_name = country_name.decode("utf8")
        country_data_obj["country_name"] = country_name
        country_data_tags = [
            "total_cases",
            "new_cases",
            "total_deaths",
            "new_deaths",
            "total_recovered",
            "active_cases",
            "critical",
        ]
        for single_country_data, country_data_tag in zip(
            country_data[1:], country_data_tags
        ):
            single_country_data_text = single_country_data.find(text=True)
            if isinstance(single_country_data_text, str):
                single_country_data_text_clean = (
                    single_country_data_text.encode("utf-8")
                    .strip()
                    .decode("utf8")
                    .replace(",", "")
                    .replace("+", "")
                    .replace("N/A", "")
                )
                country_data_obj[country_data_tag] = (
                    int(single_country_data_text_clean)
                    if single_country_data_text_clean != ""
                    else 0
                )
            else:
                country_data_obj[country_data_tag] = ""

        country_data_obj["data_received_date"] = datetime.now().strftime("%Y-%m-%d")
        corona_data.append(country_data_obj)

    #Logger.perform_method_exit_logging("data_scraping", "_parse_country_info")
    return corona_data


def scrape_corona_virus_data():
    # pylint: disable=missing-function-docstring
    #Logger.perform_method_entry_logging("data_scraping", "_parse_country_info")

    http_pool = urllib3.connection_from_url(Constants.SOURCE_ROOT_WEBSITE_URL)
    html_data = http_pool.request("GET", Constants.SOURCE_ROOT_WEBSITE_URL).data.decode(
        "utf8"
    )
    corona_data = {}
    corona_data = _parse_total_counter_info(html_data, corona_data)
    save_obj(corona_data, "Total_Corona_Virus_Data", "Data")
    country_data = []
    country_data = _parse_country_info(html_data, country_data)
    save_obj(country_data, "Country_Corona_Virus_Data", "Data")
    # Logger.log('DEBUG', 'data_scraping', 'scrape_corona_virus_data', html_data)

    #Logger.perform_method_exit_logging("data_scraping", "scrape_corona_virus_data")

if __name__ == "__main__":
    #Logger.perform_method_entry_logging("data_scraping", "scrape_corona_virus_data")
    scrape_corona_virus_data()
    #Logger.perform_method_exit_logging("data_scraping", "scrape_corona_virus_data")




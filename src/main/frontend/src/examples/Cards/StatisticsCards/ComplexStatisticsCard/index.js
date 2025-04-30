// prop-types is a library for typechecking of props
import PropTypes from "prop-types";

// @mui material components
import Card from "@mui/material/Card";
import Divider from "@mui/material/Divider";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

function ComplexStatisticsCard({
  workLocation,
  jobDescription,
  dailyWage,
  gender,
  numberOfWorkers,
}) {
  return (
    <Card>
      {/* 상부 */}
      <MDBox display="flex" justifyContent="space-between" pt={1} px={2}>
        <MDBox textAlign="left" lineHeight={1.25} width="100%">
          <MDTypography variant="button" fontWeight="light" color="text">
            {workLocation}
          </MDTypography>
          <MDTypography variant="h5">{jobDescription}</MDTypography>
          <MDTypography variant="h5">
            {typeof dailyWage === "number" ? `${dailyWage.toLocaleString()}원` : dailyWage}
          </MDTypography>
        </MDBox>
      </MDBox>
      <Divider />
      {/* 하부 */}
      <MDBox pb={2} px={2}>
        <MDTypography component="p" variant="button" display="flex" fontWeight="bold">
          {gender}
          <br />
          선발 인원 수 : {numberOfWorkers}
        </MDTypography>
      </MDBox>
    </Card>
  );
}

// Setting default values for the props of ComplexStatisticsCard
ComplexStatisticsCard.defaultProps = {
  color: "info",
  dailyWage: "Not specified",
};

// Typechecking props for the ComplexStatisticsCard
ComplexStatisticsCard.propTypes = {
  color: PropTypes.oneOf([
    "primary",
    "secondary",
    "info",
    "success",
    "warning",
    "error",
    "light",
    "dark",
  ]),
  workLocation: PropTypes.string.isRequired,
  jobDescription: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  dailyWage: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  gender: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  numberOfWorkers: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
};

export default ComplexStatisticsCard;

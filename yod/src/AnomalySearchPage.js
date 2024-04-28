import React, { useState } from 'react';
import dataBefore from './before.json';
import dataAfter from './tsconfig.json';
import { Line } from 'react-chartjs-2';
import Chart from 'chart.js/auto';

const AnomalySearchPage = () => {
    const [selectedChannel, setSelectedChannel] = useState('ch0');

    const handleChange = (e) => {
        setSelectedChannel(e.target.value);
    };

    const formatData = (data) => {
        return data.map(item => ({
            timestamp: item.timestamp,
            value: item[selectedChannel],
            target_upper: item[`target_${selectedChannel}`] === 1,
            target_lower: item[`target_${selectedChannel}`] === 0,
        }));
    };

    const chartDataBefore = {
        labels: formatData(dataBefore).map(item => item.timestamp),
        datasets: [
            {
                label: selectedChannel,
                data: formatData(dataBefore).map(item => item.value),
                borderColor: 'transparent',
                pointBackgroundColor: 'blue',
                pointRadius: 2,
                borderWidth: 1,
                borderColor: 'black',
            },
        ],
    };

    const chartDataAfter = {
        labels: formatData(dataAfter).map(item => item.timestamp),
        datasets: [
            {
                label: selectedChannel,
                data: formatData(dataAfter).map(item => item.value),
                borderColor: 'transparent',
                pointBackgroundColor: formatData(dataAfter).map(item => (item.value > 0 ? 'red' : 'green')),
                pointRadius: 2,
                borderWidth: 1,
                borderColor: 'black',
            },
        ],
    };

    return (
        <>
            <h1>Поиск аномалий во временных рядах</h1>
            <div className="container">
                <div className="left">
                    <div className="left-left">
                        <div className="graph">
                            <h3>График до поиска аномалий</h3>
                            <Line data={chartDataBefore}/>
                        </div>
                        <div className="graph">
                            <h3>График после поиска аномалий</h3>
                            <Line data={chartDataAfter}/>
                        </div>
                    </div>
                    <div className="container">
                        <select value={selectedChannel} onChange={handleChange} id="selectChannel" className="anomalies">
                            <option value="ch0">Channel 0</option>
                            <option value="ch1">Channel 1</option>
                            <option value="ch2">Channel 2</option>
                            <option value="ch3">Channel 3</option>
                            <option value="ch4">Channel 4</option>
                            <option value="ch5">Channel 5</option>
                            <option value="ch6">Channel 6</option>
                        </select>
                    </div>
                </div>
            </div>
        </>
    );
};

export default AnomalySearchPage;



// import React, { useState } from 'react';
// import data from './tsconfig.json';
// import { Line } from 'react-chartjs-2';
// import Chart from 'chart.js/auto';
//
//
// const AnomalySearchPage = () => {
//     const [selectedChannel, setSelectedChannel] = useState('ch0');
//
//     const handleChange = (e) => {
//         setSelectedChannel(e.target.value);
//     };
//
//     const filteredData = data.map(item => ({
//         timestamp: item.timestamp,
//         value: item[selectedChannel],
//         target_upper: item[`target_${selectedChannel}`] === 1,
//         target_lower: item[`target_${selectedChannel}`] === 0,
//     }));
//
//     const chartData = {
//         labels: filteredData.map(item => item.timestamp),
//         datasets: [
//             {
//                 label: selectedChannel,
//                 data: filteredData.map(item => item.value),
//                 borderColor: 'transparent', // Цвет точек
//                 pointBackgroundColor: filteredData.map(item => (item.value > 0 ? 'red' : 'green')), // Цвет точек
//                 pointRadius: 5, // Размер точек
//                 borderWidth: 1, // Толщина линии
//                 borderColor: 'black', // Цвет линии
//             },
//         ],
//     };
//
//     return (
//         <div>
//             <select value={selectedChannel} onChange={handleChange}>
//                 <option value="ch0">Channel 0</option>
//                 <option value="ch1">Channel 1</option>
//                 <option value="ch2">Channel 2</option>
//                 <option value="ch3">Channel 3</option>
//                 <option value="ch4">Channel 4</option>
//                 <option value="ch5">Channel 5</option>
//                 <option value="ch6">Channel 6</option>
//             </select>
//             <Line data={chartData} />
//         </div>
//     );
// };
//
// export default AnomalySearchPage;
//
//
// import React, { useState } from 'react';
// import data from './before.json';
// import { Line } from 'react-chartjs-2';
// import Chart from 'chart.js/auto';
//
//
// const AnomalySearchPage = () => {
//
//     return (
//         <div>
//             <select value={selectedChannel} onChange={handleChange}>
//                 <option value="ch0">Channel 0</option>
//                 <option value="ch1">Channel 1</option>
//                 <option value="ch2">Channel 2</option>
//                 <option value="ch3">Channel 3</option>
//                 <option value="ch4">Channel 4</option>
//                 <option value="ch5">Channel 5</option>
//                 <option value="ch6">Channel 6</option>
//             </select>
//             <Line data={chartData} />
//         </div>
//     );
// };
//
// export default AnomalySearchPage;

//
// import React, { useState } from 'react';
// import dataBefore from './before.json';
// import dataAfter from './tsconfig.json';
// import { Line } from 'react-chartjs-2';
// import Chart from 'chart.js/auto';
//
// const AnomalySearchPage = () => {
//     const [selectedChannel, setSelectedChannel] = useState('ch0');
//
//     const handleChange = (e) => {
//         setSelectedChannel(e.target.value);
//     };
//
//     const formatData = (data) => {
//         return data.map(item => ({
//             timestamp: item.timestamp,
//             value: item[selectedChannel],
//             target_upper: item[`target_${selectedChannel}`] === 1,
//             target_lower: item[`target_${selectedChannel}`] === 0,
//         }));
//     };
//
//     const chartDataBefore = {
//         labels: formatData(dataBefore).map(item => item.timestamp),
//         datasets: [
//             {
//                 label: selectedChannel,
//                 data: formatData(dataBefore).map(item => item.value),
//                 borderColor: 'transparent',
//                 pointBackgroundColor: 'blue',
//                 pointRadius: 5,
//                 borderWidth: 1,
//                 borderColor: 'black',
//             },
//         ],
//     };
//
//     const chartDataAfter = {
//         labels: formatData(dataAfter).map(item => item.timestamp),
//         datasets: [
//             {
//                 label: selectedChannel,
//                 data: formatData(dataAfter).map(item => item.value),
//                 borderColor: 'transparent',
//                 pointBackgroundColor: formatData(dataAfter).map(item => (item.value > 0 ? 'red' : 'green')),
//                 pointRadius: 5,
//                 borderWidth: 1,
//                 borderColor: 'black',
//             },
//         ],
//     };
//
//     return (
//         <div>
//             <select value={selectedChannel} onChange={handleChange}>
//                 <option value="ch0">Channel 0</option>
//                 <option value="ch1">Channel 1</option>
//                 <option value="ch2">Channel 2</option>
//                 <option value="ch3">Channel 3</option>
//                 <option value="ch4">Channel 4</option>
//                 <option value="ch5">Channel 5</option>
//                 <option value="ch6">Channel 6</option>
//             </select>
//             <div>
//                 <h3>График до поиска аномалий</h3>
//                 <Line data={chartDataBefore} />
//             </div>
//             <div>
//                 <h3>График после поиска аномалий</h3>
//                 <Line data={chartDataAfter} />
//             </div>
//         </div>
//     );
// };
//
// export default AnomalySearchPage;
